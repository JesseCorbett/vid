package com.jessecorbett.vid

import vue.Vue
import kotlin.js.json

typealias RenderFunction = () -> Vue.VNode

fun main() {

    fun setup(props: Any): RenderFunction {
        var count by Reactive(0)

        val next = Vue.computed { count + 1 }

        return {
            Vue.h(
                "div",
                json(
                    "onClick" to { count++ }
                ),
                "Clicked $count times. Click one more to be at ${next.value}"
            )
        }
    }

    val app = Vue.createApp(json("setup" to ::setup))
    app.config.performance = true
    app.config.devtools = true
    app.mount("#app")
}
