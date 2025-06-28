package com.jessecorbett.vid

import vid.*
import kotlin.js.json

fun main() {
    val setup = setup<Unit> {
        var count by ref(0)
        val next by computed { count + 1 }

        render {
            h(
                "div",
                json(
                    "onClick" to { count++ }
                ),
                "Clicked $count times. Click one more to be at $next"
            )
        }
    }

    val app = createApp(json("setup" to setup))
    app.mount("#app")
}
