// WITH_STDLIB
// WITH_REFLECT
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 329 -> sentence 329
 * PRIMARY LINKS: annotations, annotation-use-site-targets -> paragraph 329 -> sentence 329
 * NUMBER: 1
 * DESCRIPTION: @param: use-site target annotation on a constructor parameter works normally at runtime
 */

// TESTCASE NUMBER: 1
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.primaryConstructor

annotation class MyAnnotation
class MyClass constructor(@param:MyAnnotation val x: Int)

fun box(): String {
    val instance = MyClass(42)
    if (instance.x != 42) return "NOK: constructor parameter"
    val instance2 = MyClass(-1)
    if (instance2.x != -1) return "NOK: negative value"

    val param = MyClass::class.primaryConstructor?.parameters?.singleOrNull { it.name == "x" }
        ?: return "NOK: missing constructor parameter"
    if (param.findAnnotation<MyAnnotation>() == null) return "NOK: missing @param annotation"
    if (MyClass::x.findAnnotation<MyAnnotation>() != null) return "NOK: @param must not annotate property"
    return "OK"
}
