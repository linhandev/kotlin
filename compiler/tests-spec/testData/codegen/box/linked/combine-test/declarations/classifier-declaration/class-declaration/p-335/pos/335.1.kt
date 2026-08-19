// WITH_STDLIB
// WITH_REFLECT
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 335 -> sentence 335
 * PRIMARY LINKS: annotations, annotation-declarations -> paragraph 335 -> sentence 335
 * NUMBER: 1
 * DESCRIPTION: annotation on an inner class and the inner class works normally at runtime
 */

// TESTCASE NUMBER: 1
import kotlin.reflect.full.findAnnotation

annotation class MyAnnotation

class Outer(val prefix: String) {
    @MyAnnotation
    inner class Inner(val value: Int) {
        fun message(): String = "$prefix: $value"
    }
}

fun box(): String {
    val outer = Outer("val")
    val inner = outer.Inner(42)
    if (inner.message() != "val: 42") return "NOK: inner class method"
    val outer2 = Outer("test")
    val inner2 = outer2.Inner(-1)
    if (inner2.message() != "test: -1") return "NOK: negative value"

    if (Outer.Inner::class.findAnnotation<MyAnnotation>() == null) return "NOK: missing inner class annotation"
    return "OK"
}
