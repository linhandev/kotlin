// WITH_STDLIB
// WITH_REFLECT
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 338 -> sentence 338
 * PRIMARY LINKS: annotations, annotation-declarations -> paragraph 338 -> sentence 338
 * NUMBER: 1
 * DESCRIPTION: annotation on a data class and the data class works normally at runtime
 */

// TESTCASE NUMBER: 1
import kotlin.reflect.full.findAnnotation

annotation class MyAnnotation

@MyAnnotation
data class MyData(val x: Int, val y: String)

fun box(): String {
    val instance = MyData(42, "hello")
    if (instance.x != 42) return "NOK: x"
    if (instance.y != "hello") return "NOK: y"
    if (instance.component1() != 42) return "NOK: component1"
    if (instance.component2() != "hello") return "NOK: component2"
    val copied = instance.copy(x = 99)
    if (copied.x != 99 || copied.y != "hello") return "NOK: copy"
    if (instance.toString() != "MyData(x=42, y=hello)") return "NOK: toString"
    if (instance != MyData(42, "hello")) return "NOK: equals"
    if (instance.hashCode() != MyData(42, "hello").hashCode()) return "NOK: hashCode"

    if (MyData::class.findAnnotation<MyAnnotation>() == null) return "NOK: missing data class annotation"
    return "OK"
}
