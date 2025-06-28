package vid

import vue.App
import vue.VNode

typealias RenderFunction = () -> VNode

typealias SetupFunction<Props> = (Props) -> RenderFunction

fun createApp(options: dynamic): App {
    return vue.createApp(options)
}

fun <Props> setup(builder: SetupFunction<Props>): SetupFunction<Props> = builder

fun setup(builder: SetupFunction<Unit>): SetupFunction<Unit> = builder

fun render(builder: RenderFunction): RenderFunction = builder
