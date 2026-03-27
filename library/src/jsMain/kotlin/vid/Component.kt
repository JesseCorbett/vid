package vid

import vue.Component
import kotlin.js.json

fun <Props> defineComponent(
    name: String,
    vararg props: String,
    setup: SetupFunction<Props>
): Component {
    return vue.defineComponent(json("name" to name, "props" to arrayOf(*props), "setup" to setup))
}
