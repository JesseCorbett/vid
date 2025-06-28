package vid.html

import kotlinx.html.Entities
import kotlinx.html.Tag
import kotlinx.html.TagConsumer
import kotlinx.html.Unsafe
import kotlinx.html.org.w3c.dom.events.Event
import vue.VNode

fun renderHtml(builder: TagConsumer<*>.() -> Unit): VNode {
    return VidTagConsumer().apply(builder).finalize()
}

class VidTagConsumer : TagConsumer<VNode> {
    private val vNodeStack = mutableListOf<VNode>()

    override fun onTagStart(tag: Tag) {
        TODO("Not yet implemented")
    }

    override fun onTagAttributeChange(tag: Tag, attribute: String, value: String?) {
        TODO("Not yet implemented")
    }

    override fun onTagEvent(
        tag: Tag,
        event: String,
        value: (Event) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun onTagEnd(tag: Tag) {
        TODO("Not yet implemented")
    }

    override fun onTagContent(content: CharSequence) {
        TODO("Not yet implemented")
    }

    override fun onTagContentEntity(entity: Entities) {
        TODO("Not yet implemented")
    }

    override fun onTagContentUnsafe(block: Unsafe.() -> Unit) {
        TODO("Not yet implemented")
    }

    override fun onTagComment(content: CharSequence) {
        TODO("Not yet implemented")
    }

    override fun finalize(): VNode {
        TODO("Not yet implemented")
    }

}
