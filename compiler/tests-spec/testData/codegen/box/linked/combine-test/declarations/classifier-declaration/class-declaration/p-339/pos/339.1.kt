// WITH_STDLIB
// WITH_REFLECT
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 339 -> sentence 339
 * PRIMARY LINKS: annotations, annotation-declarations -> paragraph 339 -> sentence 339
 * NUMBER: 1
 * DESCRIPTION: annotation on a sealed class and the sealed class works normally at runtime
 */

// TESTCASE NUMBER: 1
import kotlin.reflect.full.findAnnotation

annotation class MyAnnotation

@MyAnnotation
sealed class MySealed {
    data class A(val value: Int) : MySealed()
    data class B(val text: String) : MySealed()
}

fun process(sealed: MySealed): String = when (sealed) {
    is MySealed.A -> "A: ${sealed.value}"
    is MySealed.B -> "B: ${sealed.text}"
}

fun box(): String {
    val a = MySealed.A(42)
    if (process(a) != "A: 42") return "NOK: sealed A"
    val b = MySealed.B("hello")
    if (process(b) != "B: hello") return "NOK: sealed B"

    if (MySealed::class.findAnnotation<MyAnnotation>() == null) return "NOK: missing sealed class annotation"
    return "OK"
}
