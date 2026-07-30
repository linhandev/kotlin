// WITH_STDLIB
// WITH_REFLECT
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 332 -> sentence 332
 * PRIMARY LINKS: annotations, annotation-use-site-targets -> paragraph 332 -> sentence 332
 * NUMBER: 1
 * DESCRIPTION: multiple use-site target annotations on the same property work normally at runtime
 */

// TESTCASE NUMBER: 1
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.jvm.javaField

annotation class MyAnnotation
class MyClass { @field:MyAnnotation @get:MyAnnotation val x: Int = 0 }

fun box(): String {
    val instance = MyClass()
    if (instance.x != 0) return "NOK: default value"
    val instance2 = MyClass()
    if (instance2.x != 0) return "NOK: second instance"

    val field = MyClass::x.javaField ?: return "NOK: missing backing field"
    if (field.annotations.none { it is MyAnnotation }) return "NOK: missing @field annotation"
    if (MyClass::x.getter.findAnnotation<MyAnnotation>() == null) return "NOK: missing @get annotation"
    if (MyClass::x.findAnnotation<MyAnnotation>() != null) return "NOK: must not annotate property itself"
    return "OK"
}
