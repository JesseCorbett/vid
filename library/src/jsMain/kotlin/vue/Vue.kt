@file:JsModule("vue")
package vue

import kotlin.js.Promise

external interface VNode

external class Plugin

external val version: String

external fun nextTick(callback: () -> Unit): Promise<Unit>
