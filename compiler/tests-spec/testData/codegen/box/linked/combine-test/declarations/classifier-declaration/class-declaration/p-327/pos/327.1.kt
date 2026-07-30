// WITH_STDLIB
// WITH_REFLECT
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 327 -> sentence 327
 * PRIMARY LINKS: annotations, annotation-use-site-targets -> paragraph 327 -> sentence 327
 * NUMBER: 1
 * DESCRIPTION: @get: use-site target annotation on a class property works normally at runtime
 */

// TESTCASE NUMBER: 1
import kotlin.reflect.full.findAnnotation

annotation class MyAnnotation
class MyClass(val backing: Int = 0) { @get:MyAnnotation val x: Int get() = backing }

fun box(): String {
    val instance = MyClass(42)
    if (instance.x != 42) return "NOK: getter forwarding"
    val instance2 = MyClass(-1)
    if (instance2.x != -1) return "NOK: negative value"

    if (MyClass::x.getter.findAnnotation<MyAnnotation>() == null) return "NOK: missing @get annotation"
    if (MyClass::x.findAnnotation<MyAnnotation>() != null) return "NOK: @get must not annotate property"
    return "OK"
}
