// WITH_STDLIB
// WITH_REFLECT
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 346 -> sentence 346
 * PRIMARY LINKS: annotations, annotation-declarations -> paragraph 346 -> sentence 346
 * NUMBER: 1
 * DESCRIPTION: annotation with RUNTIME retention and the annotated class works normally at runtime
 */

// TESTCASE NUMBER: 1
import kotlin.reflect.full.findAnnotation

@Retention(AnnotationRetention.RUNTIME)
annotation class MyAnnotation

@MyAnnotation
class MyClass(val x: Int)

fun box(): String {
    val instance = MyClass(42)
    if (instance.x != 42) return "NOK: property access"
    if (MyClass::class.findAnnotation<MyAnnotation>() == null) {
        return "NOK: RUNTIME retention annotation missing at runtime"
    }
    return "OK"
}
