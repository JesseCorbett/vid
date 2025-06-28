package vid

import vue.App
import vue.VNode
import kotlin.js.json

typealias RenderFunction = () -> VNode

typealias SetupFunction<Props> = (Props) -> RenderFunction

fun createApp(options: dynamic): App {
    return vue.createApp(options)
}

fun createApp(builder: SetupFunction<Unit>): App {
    return createApp(json("setup" to builder))
}

fun <Props> setup(builder: SetupFunction<Props>): SetupFunction<Props> = builder

fun setup(builder: SetupFunction<Unit>): SetupFunction<Unit> = builder

fun render(builder: RenderFunction): RenderFunction = builder
