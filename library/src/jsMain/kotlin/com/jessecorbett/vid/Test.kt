package com.jessecorbett.vid

import kotlinx.html.br
import kotlinx.html.button
import kotlinx.html.div
import kotlinx.html.js.onClickFunction
import kotlinx.html.span
import vid.computed
import vid.createApp
import vid.defineComponent
import vid.html.component
import vid.html.invoke
import vid.html.renderHtml
import vid.ref

fun main() {
    class TickerProps(val count: String)
    val ticker = defineComponent<TickerProps>("Ticker") { props ->
        renderHtml {
            println(JSON.stringify(props))
            span { +"You have clicked the button ${props.count} times." }
        }
    }

    val app = createApp {
        var count by ref(0)
        val next by computed { count + 1 }

        renderHtml {
            div {
                ticker {
                    attributes["count"] = count.toString()
                }
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
