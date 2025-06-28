@file:JsModule("vue")
package vue

import org.w3c.dom.Element
import kotlin.js.Json

external fun createApp(options: dynamic): App

external fun createSSRApp(options: dynamic): App

external class App {
    fun mount(element: String)

    fun mount(element: Element)

    fun unmount()

    fun onUnmounted(callback: () -> Unit)

    fun component(name: String): Component?

    fun component(name: String, component: Component): App

    fun directive(name: String): Directive?

    fun directive(name: String, directive: Directive): App

    fun directive(name: String, directive: () -> Unit): App

    fun use(plugin: Plugin, vararg options: Any?): App

    fun <T> provide(key: String, value: T): App

    fun <T> runWithContext(fn: () -> T): T

    val version: String

    val config: Config

    interface Config {
        var errorHandler: (dynamic, dynamic, String) -> Unit
        var warnHandler: (String, dynamic, String) -> Unit
        var performance: Boolean
        val compilerOptions: CompilerOptions

        interface CompilerOptions {
            var dev: Boolean
            var strict: Boolean
            var mode: String
            var isCustomElement: (tag: String) -> Boolean
            var whitespace: String
            var delimiters: Array<String>
            var comments: Boolean
            var globalProperties: Json
            var optionMergeStrategies: MutableMap<String, (dynamic, dynamic) -> dynamic>
            var idPrefix: String?
            var throwUnhandledErrorInProduction: Boolean
        }
    }
}
