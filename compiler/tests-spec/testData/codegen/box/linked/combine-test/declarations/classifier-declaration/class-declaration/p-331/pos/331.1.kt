// WITH_STDLIB
// WITH_REFLECT
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 331 -> sentence 331
 * PRIMARY LINKS: annotations, annotation-use-site-targets -> paragraph 331 -> sentence 331
 * NUMBER: 1
 * DESCRIPTION: @delegate: use-site target annotation on a delegated property works normally at runtime
 */

// TESTCASE NUMBER: 1
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

annotation class MyAnnotation
class MyClass { @delegate:MyAnnotation val x: String by lazy { "hello" } }

fun box(): String {
    val instance = MyClass()
    if (instance.x != "hello") return "NOK: delegated value"
    val instance2 = MyClass()
    if (instance2.x != "hello") return "NOK: second instance"

    // @delegate targets the hidden delegate field (not property/getter in the Kotlin reflection model)
    val prop = MyClass::class.memberProperties.single { it.name == "x" }
    if (prop.findAnnotation<MyAnnotation>() != null) return "NOK: @delegate must not annotate property"
    if (prop.getter.findAnnotation<MyAnnotation>() != null) return "NOK: @delegate must not annotate getter"
    prop.isAccessible = true
    val delegate = prop.getDelegate(instance) ?: return "NOK: missing delegate instance"
    if (delegate !is Lazy<*>) return "NOK: unexpected delegate type"
    return "OK"
}
