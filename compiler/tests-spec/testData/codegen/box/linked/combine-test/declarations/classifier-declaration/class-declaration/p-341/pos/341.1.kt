// WITH_STDLIB
// WITH_REFLECT
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 341 -> sentence 341
 * PRIMARY LINKS: annotations, annotation-declarations -> paragraph 341 -> sentence 341
 * NUMBER: 1
 * DESCRIPTION: annotation on an annotation class and the annotation class works normally at runtime
 */

// TESTCASE NUMBER: 1
import kotlin.reflect.full.findAnnotation

annotation class MyAnnotation

@MyAnnotation
annotation class MyAnnotatedAnnotation(val message: String)

@MyAnnotatedAnnotation("hello")
class MyClass(val value: Int)

fun box(): String {
    val instance = MyClass(42)
    if (instance.value != 42) return "NOK: property access"

    if (MyAnnotatedAnnotation::class.findAnnotation<MyAnnotation>() == null) {
        return "NOK: missing annotation-on-annotation"
    }
    val used = MyClass::class.findAnnotation<MyAnnotatedAnnotation>()
        ?: return "NOK: missing annotated annotation on class"
    if (used.message != "hello") return "NOK: wrong annotated annotation payload"
    return "OK"
}
