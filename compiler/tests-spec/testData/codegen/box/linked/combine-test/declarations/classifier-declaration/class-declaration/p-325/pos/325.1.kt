// WITH_STDLIB
// WITH_REFLECT
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 325 -> sentence 325
 * PRIMARY LINKS: annotations, annotation-use-site-targets -> paragraph 325 -> sentence 325
 * NUMBER: 1
 * DESCRIPTION: @field: use-site target annotation on a class property works normally at runtime
 */

// TESTCASE NUMBER: 1
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.jvm.javaField

annotation class MyAnnotation
class MyClass { @field:MyAnnotation val x: Int = 0 }

fun box(): String {
    val instance = MyClass()
    if (instance.x != 0) return "NOK: default value"
    val instance2 = MyClass()
    if (instance2.x != 0) return "NOK: second instance"

    // @field targets the backing field; exposed via kotlin.reflect.jvm.javaField
    val field = MyClass::x.javaField ?: return "NOK: missing backing field"
    if (field.annotations.none { it is MyAnnotation }) return "NOK: missing @field annotation"
    if (MyClass::x.findAnnotation<MyAnnotation>() != null) return "NOK: @field must not annotate property"
    if (MyClass::x.getter.findAnnotation<MyAnnotation>() != null) return "NOK: @field must not annotate getter"
    return "OK"
}
