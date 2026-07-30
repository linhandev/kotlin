// WITH_STDLIB
// WITH_REFLECT
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 326 -> sentence 326
 * PRIMARY LINKS: annotations, annotation-use-site-targets -> paragraph 326 -> sentence 326
 * NUMBER: 1
 * DESCRIPTION: @property: use-site target annotation on a class property works normally at runtime
 */

// TESTCASE NUMBER: 1
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.jvm.javaField

annotation class MyAnnotation
class MyClass { @property:MyAnnotation val x: Int = 0 }

fun box(): String {
    val instance = MyClass()
    if (instance.x != 0) return "NOK: default value"
    val instance2 = MyClass()
    if (instance2.x != 0) return "NOK: second instance"

    if (MyClass::x.findAnnotation<MyAnnotation>() == null) return "NOK: missing @property annotation"
    if (MyClass::x.getter.findAnnotation<MyAnnotation>() != null) return "NOK: @property must not annotate getter"
    if (MyClass::x.javaField?.annotations?.any { it is MyAnnotation } == true) {
        return "NOK: @property must not annotate field"
    }
    return "OK"
}
