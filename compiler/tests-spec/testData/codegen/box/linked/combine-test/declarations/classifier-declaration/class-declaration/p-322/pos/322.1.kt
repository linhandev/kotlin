// WITH_STDLIB
// WITH_REFLECT
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 322 -> sentence 322
 * PRIMARY LINKS: annotations, annotation-declarations -> paragraph 322 -> sentence 322
 * NUMBER: 1
 * DESCRIPTION: annotation on primary constructor and the class works normally at runtime
 */

// TESTCASE NUMBER: 1
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.primaryConstructor

annotation class MyAnnotation
class MyClass @MyAnnotation constructor(val x: Int)

fun box(): String {
    val instance = MyClass(42)
    if (instance.x != 42) return "NOK: constructor/accessor"
    val instance2 = MyClass(-1)
    if (instance2.x != -1) return "NOK: negative value"

    val ctor = MyClass::class.primaryConstructor ?: return "NOK: missing primary constructor"
    if (ctor.findAnnotation<MyAnnotation>() == null) return "NOK: missing constructor annotation"
    return "OK"
}
