// WITH_STDLIB
// WITH_REFLECT
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 343 -> sentence 343
 * PRIMARY LINKS: annotations, annotation-declarations -> paragraph 343 -> sentence 343
 * NUMBER: 1
 * DESCRIPTION: annotation with vararg parameter and the annotated class works normally at runtime
 */

// TESTCASE NUMBER: 1
import kotlin.reflect.full.findAnnotation

annotation class MyAnnotation(vararg val values: String)

@MyAnnotation("a", "b", "c")
class MyClass(val x: Int)

fun box(): String {
    val instance = MyClass(42)
    if (instance.x != 42) return "NOK: property access"
    val ann = MyClass::class.findAnnotation<MyAnnotation>()
        ?: return "NOK: vararg annotation missing"
    if (!ann.values.contentEquals(arrayOf("a", "b", "c"))) return "NOK: vararg annotation values"
    return "OK"
}
