// WITH_STDLIB
// WITH_REFLECT
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 350 -> sentence 350
 * PRIMARY LINKS: annotations, annotation-use-site-targets -> paragraph 350 -> sentence 350
 * NUMBER: 1
 * DESCRIPTION: @field annotation on constructor parameter and the class works normally at runtime
 */

// TESTCASE NUMBER: 1
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.jvm.javaField

annotation class MyAnnotation

class MyClass(@field:MyAnnotation val x: Int)

fun box(): String {
    val instance = MyClass(42)
    if (instance.x != 42) return "NOK: property access"

    val field = MyClass::x.javaField ?: return "NOK: missing backing field"
    if (field.annotations.none { it is MyAnnotation }) return "NOK: missing @field on constructor parameter"
    if (MyClass::x.findAnnotation<MyAnnotation>() != null) return "NOK: @field must not annotate property"
    return "OK"
}
