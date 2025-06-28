package com.jessecorbett.vid

import vue.Vue
import kotlin.reflect.KProperty

class Reactive<T>(value: T) {
    private val ref = Vue.ref(value)

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return ref.value
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        ref.value = value
    }
}
