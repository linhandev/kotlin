// WITH_STDLIB
// WITH_REFLECT
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 345 -> sentence 345
 * PRIMARY LINKS: annotations, annotation-declarations -> paragraph 345 -> sentence 345
 * NUMBER: 1
 * DESCRIPTION: multiple annotations on the same class and the class works normally at runtime
 */

// TESTCASE NUMBER: 1
import kotlin.reflect.full.findAnnotation

annotation class Outer
annotation class Inner

@Outer @Inner
class MyClass(val x: Int)

fun box(): String {
    val instance = MyClass(42)
    if (instance.x != 42) return "NOK: property access"
    if (MyClass::class.findAnnotation<Outer>() == null) return "NOK: missing Outer annotation"
    if (MyClass::class.findAnnotation<Inner>() == null) return "NOK: missing Inner annotation"
    return "OK"
}
