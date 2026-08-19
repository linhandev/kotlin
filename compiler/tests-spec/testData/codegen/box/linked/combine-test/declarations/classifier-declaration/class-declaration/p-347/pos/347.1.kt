// WITH_STDLIB
// WITH_REFLECT
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 347 -> sentence 347
 * PRIMARY LINKS: annotations, annotation-declarations -> paragraph 347 -> sentence 347
 * NUMBER: 1
 * DESCRIPTION: annotation restricted to CLASS target and the annotated class works normally at runtime
 */

// TESTCASE NUMBER: 1
import kotlin.reflect.full.findAnnotation

@Target(AnnotationTarget.CLASS)
annotation class ClassOnly

@ClassOnly
class MyClass(val x: Int)

fun box(): String {
    val instance = MyClass(42)
    if (instance.x != 42) return "NOK: property access"
    if (MyClass::class.findAnnotation<ClassOnly>() == null) {
        return "NOK: CLASS-target annotation missing at runtime"
    }
    return "OK"
}
