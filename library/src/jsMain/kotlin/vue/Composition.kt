@file:JsModule("vue")
package vue

external interface Ref<T> : Reactive<T> {
    override var value: T
}

external fun <T> ref(value: T): Ref<T>

external interface Reactive<T> {
    val value: T
}

external fun <T> computed(getter: () -> T): Reactive<T>
