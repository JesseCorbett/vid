@file:JsModule("vue")
package vue

external class Component : VNode

external fun defineComponent(component: ComponentOptions): dynamic

external fun defineComponent(setup: (Any) -> Unit)

external fun defineComponent(
    setup: (Any) -> Unit,
    extraOptions: ComponentOptions
): () -> Any

external interface ComponentOptions {

}
