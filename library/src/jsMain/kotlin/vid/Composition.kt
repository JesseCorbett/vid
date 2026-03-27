package vid

import vue.Reactive
import vue.Ref
import kotlin.reflect.KProperty

fun <T> ref(value: T): RefDelegate<T> {
    val ref = vue.ref(value)
    return RefDelegate(ref)
}

class RefDelegate<T>(private val ref: Ref<T>) {

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return ref.value
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        ref.value = value
    }
}

fun <T> computed(getter: () -> T): ComputedDelegate<T> {
    val computed = vue.computed(getter)
    return ComputedDelegate(computed)
}

class ComputedDelegate<T>(private val reactive: Reactive<T>) {

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return reactive.value
    }
}

fun <T> injectFactory(key: String, defaultValue: () -> T): T {
    return vue.inject(key, defaultValue, true)
}
