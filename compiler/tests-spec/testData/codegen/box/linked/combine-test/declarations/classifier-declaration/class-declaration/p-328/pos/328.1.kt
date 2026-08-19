// WITH_STDLIB
// WITH_REFLECT
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 328 -> sentence 328
 * PRIMARY LINKS: annotations, annotation-use-site-targets -> paragraph 328 -> sentence 328
 * NUMBER: 1
 * DESCRIPTION: @set: use-site target annotation on a class property works normally at runtime
 */

// TESTCASE NUMBER: 1
import kotlin.reflect.full.findAnnotation

annotation class MyAnnotation
class MyClass { @set:MyAnnotation var x: Int = 0 }

fun box(): String {
    val instance = MyClass()
    if (instance.x != 0) return "NOK: initial value"
    instance.x = 42
    if (instance.x != 42) return "NOK: after set"
    instance.x = -1
    if (instance.x != -1) return "NOK: negative set"

    if (MyClass::x.setter.findAnnotation<MyAnnotation>() == null) return "NOK: missing @set annotation"
    if (MyClass::x.findAnnotation<MyAnnotation>() != null) return "NOK: @set must not annotate property"
    return "OK"
}
