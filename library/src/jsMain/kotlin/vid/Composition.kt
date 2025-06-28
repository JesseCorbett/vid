package vid

import kotlin.reflect.KProperty

fun <T> ref(value: T): RefDelegate<T> {
    return RefDelegate(value)
}

class RefDelegate<T>(value: T) {
    private val ref = vue.ref(value)

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return ref.value
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        ref.value = value
    }
}

fun <T> computed(getter: () -> T): ComputedDelegate<T> {
    return ComputedDelegate(getter)
}

class ComputedDelegate<T>(getter: () -> T) {
    private val reactive = vue.computed(getter)

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return reactive.value
    }
}
