// WITH_STDLIB
// WITH_REFLECT
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 340 -> sentence 340
 * PRIMARY LINKS: annotations, annotation-declarations -> paragraph 340 -> sentence 340
 * NUMBER: 1
 * DESCRIPTION: annotation on an inline value class and the value class works normally at runtime
 */

// TESTCASE NUMBER: 1
import kotlin.reflect.full.findAnnotation

annotation class MyAnnotation

@MyAnnotation
@JvmInline
value class MyValue(val x: Int) {
    fun compute(): Int = x * 2
}

fun box(): String {
    val instance = MyValue(21)
    if (instance.compute() != 42) return "NOK: value class method"
    if (instance.x != 21) return "NOK: value class property"
    val instance2 = MyValue(0)
    if (instance2.compute() != 0) return "NOK: zero value"

    if (MyValue::class.findAnnotation<MyAnnotation>() == null) return "NOK: missing value class annotation"
    return "OK"
}
