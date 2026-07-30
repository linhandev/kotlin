// WITH_STDLIB
// WITH_REFLECT
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 336 -> sentence 336
 * PRIMARY LINKS: annotations, annotation-declarations -> paragraph 336 -> sentence 336
 * NUMBER: 1
 * DESCRIPTION: annotation on a nested class and the nested class works normally at runtime
 */

// TESTCASE NUMBER: 1
import kotlin.reflect.full.findAnnotation

annotation class MyAnnotation

class Outer {
    @MyAnnotation
    class Nested(val value: Int) {
        fun compute(): Int = value * 2
    }
}

fun box(): String {
    val instance = Outer.Nested(21)
    if (instance.compute() != 42) return "NOK: nested class method"
    val instance2 = Outer.Nested(0)
    if (instance2.compute() != 0) return "NOK: zero value"

    if (Outer.Nested::class.findAnnotation<MyAnnotation>() == null) return "NOK: missing nested class annotation"
    return "OK"
}
