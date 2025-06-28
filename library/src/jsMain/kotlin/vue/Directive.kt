@file:JsModule("vue")
package vue

import org.w3c.dom.Element

external interface Directive {
    val created: (Element, dynamic, VNode) -> Unit
    val beforeMount: (Element, dynamic, VNode) -> Unit
    val mounted: (Element, dynamic, VNode) -> Unit
    val beforeUpdate: (Element, dynamic, VNode, VNode) -> Unit
    val updated: (Element, dynamic, VNode, VNode) -> Unit
    val beforeUnmount: (Element, dynamic, VNode) -> Unit
    val unmounted: (Element, dynamic, VNode) -> Unit
}
