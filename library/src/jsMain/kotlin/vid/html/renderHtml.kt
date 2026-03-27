package vid.html

import kotlinx.html.Entities
import kotlinx.html.HTMLTag
import kotlinx.html.HtmlInlineTag
import kotlinx.html.Tag
import kotlinx.html.TagConsumer
import kotlinx.html.Unsafe
import kotlinx.html.org.w3c.dom.events.Event
import kotlinx.html.visit
import vid.RenderFunction
import vue.Component
import vue.VNode
import kotlin.js.json

inline fun renderHtml(crossinline builder: TagConsumer<*>.() -> Unit): RenderFunction {
    return { VidTagConsumer().apply(builder).finalize() }
}

class COMPONENT(consumer: TagConsumer<*>, val component: Component) : HTMLTag(
    tagName = "vue-component",
    consumer = consumer,
    initialAttributes = emptyMap(),
    inlineTag = false,
    emptyTag = false
), HtmlInlineTag

fun Tag.component(component: Component, block: COMPONENT.() -> Unit) {
    COMPONENT(consumer, component).visit(block)
}

context(tag: Tag)
operator fun Component.invoke(block: COMPONENT.() -> Unit = {}) {
    tag.component(this, block)
}

class VidTagConsumer : TagConsumer<VNode> {
    sealed interface VNodeContent
    sealed interface VNodeContentWithChildren : VNodeContent {
        val attrs: MutableMap<String, String>
        val children: MutableList<VNodeContent>
        val events: MutableMap<String, (Event) -> Unit>
    }
    class VNodeBuilder(
        val tag: String,
        override val attrs: MutableMap<String, String>,
        override val children: MutableList<VNodeContent> = mutableListOf(),
        override val events: MutableMap<String, (Event) -> Unit> = mutableMapOf()
    ) : VNodeContentWithChildren
    class VNodeText(val text: String) : VNodeContent
    class VNodeComponent(
        val component: Component,
        override val attrs: MutableMap<String, String>,
        override val children: MutableList<VNodeContent> = mutableListOf(),
        override val events: MutableMap<String, (Event) -> Unit> = mutableMapOf()
    ) : VNodeContentWithChildren

    private val vNodeStack = mutableListOf<VNodeContentWithChildren>()
    private val currentVNode: VNodeContentWithChildren?
        get() = vNodeStack.lastOrNull()

    override fun onTagStart(tag: Tag) {
        val builder = if (tag is COMPONENT) {
            VNodeComponent(tag.component, attrs = tag.attributes)
        } else {
            VNodeBuilder(tag.tagName, attrs = tag.attributes)
        }
        currentVNode?.children?.add(builder)
        vNodeStack.add(builder)
    }

    override fun onTagAttributeChange(tag: Tag, attribute: String, value: String?) {
        if (value == null) {
            currentVNode!!.attrs.remove(attribute)
        } else {
            currentVNode!!.attrs[attribute] = value
        }
    }

    override fun onTagEvent(
        tag: Tag,
        event: String,
        value: (Event) -> Unit
    ) {
        currentVNode!!.events[event] = value
    }

    override fun onTagEnd(tag: Tag) {
        if (vNodeStack.size > 1) {
            vNodeStack.removeLast()
        }
    }

    override fun onTagContent(content: CharSequence) {
        currentVNode!!.children.add(VNodeText(content.toString()))
    }

    override fun onTagContentEntity(entity: Entities) {
        currentVNode!!.children.add(VNodeText(entity.text))
    }

    override fun onTagContentUnsafe(block: Unsafe.() -> Unit) {
        // Unimplemented
    }

    override fun onTagComment(content: CharSequence) {
        // Unimplemented
    }

    override fun finalize(): VNode {
        return currentVNode!!.finalize()
    }

    private fun VNodeContentWithChildren.finalize(): VNode {
        // TODO: Convert attrs to vue's expected format
        fun capitalizeThirdLetter(input: String): String {
            return "${input.substring(0, 2)}${input[2].uppercase()}${input.substring(3)}"
        }

        val adaptedEvents = events.map { capitalizeThirdLetter(it.key) to it.value }
        val adaptedProps = attrs.map { it.key to it.value }

        val props = json(*(adaptedEvents + adaptedProps).toTypedArray())
        val node = when (this) {
            is VNodeBuilder -> vue.h(tag, props, children.map {
                when (it) {
                    is VNodeContentWithChildren -> it.finalize()
                    is VNodeText -> it.text
                }
            }.toTypedArray())
            is VNodeComponent -> vue.h(component, props, {
                children.map {
                    when (it) {
                        is VNodeContentWithChildren -> it.finalize()
                        is VNodeText -> it.text
                    }
                }.toTypedArray()
            })
        }
        return node
    }
}
