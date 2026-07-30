// WITH_STDLIB
// WITH_REFLECT
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 321 -> sentence 321
 * PRIMARY LINKS: annotations, annotation-declarations -> paragraph 321 -> sentence 321
 * NUMBER: 1
 * DESCRIPTION: annotation applied to a class declaration and the class works normally at runtime
 */

// TESTCASE NUMBER: 1
import kotlin.reflect.full.findAnnotation

annotation class MyAnnotation
@MyAnnotation class MyClass(val value: Int)

fun box(): String {
    val instance = MyClass(42)
    if (instance.value != 42) return "NOK: constructor/accessor"
    val instance2 = MyClass(-1)
    if (instance2.value != -1) return "NOK: negative value"

    if (MyClass::class.findAnnotation<MyAnnotation>() == null) return "NOK: missing class annotation"
    return "OK"
}
