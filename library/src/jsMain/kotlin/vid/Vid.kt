package vid

import vue.App
import vue.Reactive
import vue.VNode
import kotlin.js.json

typealias RenderFunction = () -> VNode

external interface SetupContext {
    fun expose(data: dynamic)
}

typealias SetupFunction<Props> = (Props) -> RenderFunction

fun createApp(builder: SetupFunction<Unit>): App {
    return vue.createApp(json("setup" to builder))
}

fun <Props> setup(builder: SetupFunction<Props>): SetupFunction<Props> = builder

fun setup(builder: SetupFunction<Unit>): SetupFunction<Unit> = builder
