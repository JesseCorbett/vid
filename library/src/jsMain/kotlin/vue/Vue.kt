package vue

@JsModule("vue")
external object Vue {
    interface VNode

    fun h(element: String, props: dynamic, children: dynamic): VNode

    class VueApp {
        fun mount(element: String)
        val config: dynamic
    }

    fun createApp(options: dynamic): VueApp

    interface Ref<T> {
        var value: T
    }

    fun <T> ref(value: T): Ref<T>

    interface ReadonlyRef<T> {
        val value: T
    }

    fun <T> computed(getter: () -> T): ReadonlyRef<T>
}
