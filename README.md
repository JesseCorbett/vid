# Vid - Vue 3 Composition Wrapper for Kotlin/JS

Vid is a Kotlin/JS library designed to simplify Vue 3 development by providing a composition wrapper and a set of Kotlin utilities, including seamless integration with `kotlinx.html`.

## What is Vid?

Vid aims to bring the power and type safety of Kotlin to Vue 3 applications, leveraging the Composition API. It allows developers to write Vue components and reactive logic entirely in Kotlin, compiling to JavaScript for execution in the browser.

Key features include:
*   **Vue 3 Composition API in Kotlin**: Write reactive state, computed properties, and lifecycle hooks using idiomatic Kotlin.
*   **Kotlinx.html Integration**: Define your component templates using the `kotlinx.html` DSL, providing a type-safe and programmatic way to build HTML.
*   **Kotlin Utilities**: A collection of helpful utilities to streamline common Vue development patterns in Kotlin.

## How it Works

Vid acts as a bridge between Kotlin/JS and Vue 3. It provides Kotlin-friendly wrappers around Vue's core functionalities, allowing you to define components, reactive data, and methods using Kotlin syntax. When compiled, your Kotlin code generates JavaScript that interacts directly with the Vue 3 runtime.

The `kotlinx.html` integration means you can define your component's render function using a familiar Kotlin DSL, which then gets translated into the appropriate VNodes for Vue to render.

## Basic Example

Here's a quick example of a simple counter component using Vid:

```kotlin
import dev.jessecorbett.vid.vue.*
import kotlinx.html.*
import kotlinx.html.dom.append

fun main() {
    createApp {
        var count by ref(0)

        renderHtml {
            div {
                h1 { +"Counter: $count" }
                button {
                    onClickFunction = { count++ }
                    +"Increment"
                }
            }
        }
    }.mount("#app")
}
```

And the corresponding `index.html`:

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Vid Vue 3 App</title>
</head>
<body>
    <div id="app"></div>
    <script src="your-module-name.js"></script> <!-- Replace with your actual compiled JS file -->
</body>
</html>
```
