package vid.html

import kotlinx.html.Entities
import kotlinx.html.Tag
import kotlinx.html.TagConsumer
import kotlinx.html.Unsafe
import kotlinx.html.org.w3c.dom.events.Event
import vid.RenderFunction
import vid.h
import vue.VNode
import kotlin.js.json

fun renderHtml(builder: TagConsumer<*>.() -> Unit): RenderFunction {
    return { VidTagConsumer().apply(builder).finalize() }
}

class VidTagConsumer : TagConsumer<VNode> {
    sealed interface VNodeContent
    class VNodeBuilder(
        val tag: String,
        val attrs: MutableMap<String, String>,
        val children: MutableList<VNodeContent> = mutableListOf(),
        val events: MutableMap<String, (Event) -> Unit> = mutableMapOf()
    ) : VNodeContent
    class VNodeText(val text: String) : VNodeContent

    private val vNodeStack = mutableListOf<VNodeBuilder>()
    private val currentVNode: VNodeBuilder?
        get() = vNodeStack.lastOrNull()

    override fun onTagStart(tag: Tag) {
        val builder = VNodeBuilder(tag.tagName, attrs = tag.attributes)
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

    private fun VNodeBuilder.finalize(): VNode {
        // TODO: Convert attrs to vue's expected format
        fun capitalizeThirdLetter(input: String): String {
            return "${input.substring(0, 2)}${input[2].uppercase()}${input.substring(3)}"
        }

        val adaptedEvents = events.map { capitalizeThirdLetter(it.key) to it.value }
        val adaptedProps = attrs.map { it.key to it.value }

        val props = json(*(adaptedEvents + adaptedProps).toTypedArray())
        val node = h(tag, props, children.map {
            when (it) {
                is VNodeBuilder -> it.finalize()
                is VNodeText -> it.text
            }
        }.toTypedArray())
        return node
    }
}
