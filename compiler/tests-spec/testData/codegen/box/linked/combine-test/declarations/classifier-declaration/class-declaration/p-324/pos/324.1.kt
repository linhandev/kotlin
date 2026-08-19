// WITH_STDLIB
// WITH_REFLECT
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 324 -> sentence 324
 * PRIMARY LINKS: annotations, annotation-declarations -> paragraph 324 -> sentence 324
 * NUMBER: 1
 * DESCRIPTION: annotation on a class method and the method works normally at runtime
 */

// TESTCASE NUMBER: 1
import kotlin.reflect.full.findAnnotation

annotation class MyAnnotation
class MyClass { @MyAnnotation fun foo(x: Int): Int = x + 1 }

fun box(): String {
    val instance = MyClass()
    if (instance.foo(1) != 2) return "NOK: basic call"
    if (instance.foo(0) != 1) return "NOK: zero input"
    if (instance.foo(-1) != 0) return "NOK: negative input"

    if (MyClass::foo.findAnnotation<MyAnnotation>() == null) return "NOK: missing method annotation"
    return "OK"
}
