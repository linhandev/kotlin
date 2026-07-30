// WITH_STDLIB
// WITH_REFLECT
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 323 -> sentence 323
 * PRIMARY LINKS: annotations, annotation-declarations -> paragraph 323 -> sentence 323
 * NUMBER: 1
 * DESCRIPTION: annotation on a class property and the property works normally at runtime
 */

// TESTCASE NUMBER: 1
import kotlin.reflect.full.findAnnotation

annotation class MyAnnotation
class MyClass { @MyAnnotation val x: Int = 0 }

fun box(): String {
    val instance = MyClass()
    if (instance.x != 0) return "NOK: default value"
    val instance2 = MyClass()
    if (instance2.x != 0) return "NOK: second instance"

    if (MyClass::x.findAnnotation<MyAnnotation>() == null) return "NOK: missing property annotation"
    return "OK"
}
