@file:JsModule("vue")
package vue

import kotlin.js.Promise

external interface VNode {
    val type: String
    val props: dynamic
    val children: Array<dynamic>
    val key: String?
}

external class Plugin

external val version: String

external fun nextTick(callback: () -> Unit): Promise<Unit>
