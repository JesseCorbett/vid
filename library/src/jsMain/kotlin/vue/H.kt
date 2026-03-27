@file:JsModule("vue")
package vue

external fun h(element: String, props: dynamic, children: dynamic): VNode

external fun h(component: Component, props: dynamic, children: dynamic): VNode
