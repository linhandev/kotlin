// WITH_STDLIB
// WITH_REFLECT
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 344 -> sentence 344
 * PRIMARY LINKS: annotations, annotation-declarations -> paragraph 344 -> sentence 344
 * NUMBER: 1
 * DESCRIPTION: annotation with KClass parameter and the annotated class works normally at runtime
 */

// TESTCASE NUMBER: 1
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation

annotation class MyAnnotation(val cls: KClass<*>)

@MyAnnotation(String::class)
class MyClass(val x: Int)

fun box(): String {
    val instance = MyClass(42)
    if (instance.x != 42) return "NOK: property access"
    val ann = MyClass::class.findAnnotation<MyAnnotation>()
        ?: return "NOK: KClass annotation missing"
    if (ann.cls != String::class) return "NOK: KClass annotation parameter"
    return "OK"
}
