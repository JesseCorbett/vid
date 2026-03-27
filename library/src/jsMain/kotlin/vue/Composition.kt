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

external fun onMounted(callback: () -> Unit)

external fun onUnmounted(callback: () -> Unit)

external fun onBeforeMount(callback: () -> Unit)

external fun onBeforeUnmount(callback: () -> Unit)

external fun onUpdated(callback: () -> Unit)

external fun <T> provide(key: String, value: T)

external fun <T> inject(key: String): T?

external fun <T> inject(key: String, defaultValue: T): T

/**
 * @param treatDefaultAsFactory Should always be true, the default value will be treated as a factory function that returns the default value
 */
external fun <T> inject(key: String, defaultValue: () -> T, treatDefaultAsFactory: Boolean): T

external fun useId(): String

external fun useModel(props: Map<String, Any?>, options: dynamic): Array<Any?>
