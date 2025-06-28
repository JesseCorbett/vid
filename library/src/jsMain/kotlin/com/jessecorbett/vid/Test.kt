package com.jessecorbett.vid

import kotlinx.html.br
import kotlinx.html.button
import kotlinx.html.div
import kotlinx.html.js.onClickFunction
import kotlinx.html.span
import vid.computed
import vid.createApp
import vid.html.renderHtml
import vid.ref

fun main() {
    val app = createApp {
        var count by ref(0)
        val next by computed { count + 1 }

        renderHtml {
            div {
                span { +"You have clicked the button $count times, care to go for $next?" }
                br
                button {
                    onClickFunction = { count++ }
                    +"Click me"
                }
            }
        }
    }

    app.mount("#app")
}
