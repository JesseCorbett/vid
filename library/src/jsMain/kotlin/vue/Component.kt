@file:JsModule("vue")
package vue

import kotlin.js.Promise

external interface Component

external fun defineComponent(options: dynamic?): Component

external fun defineAsyncComponent(loader: () -> Promise<Component>): Component

external interface SetupContext {
    fun expose(data: dynamic)
}
