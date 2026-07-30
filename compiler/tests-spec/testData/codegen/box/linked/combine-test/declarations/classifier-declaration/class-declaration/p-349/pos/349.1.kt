// WITH_STDLIB
// WITH_REFLECT
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 349 -> sentence 349
 * PRIMARY LINKS: annotations, annotation-declarations -> paragraph 349 -> sentence 349
 * NUMBER: 1
 * DESCRIPTION: annotation on companion object and the class works normally at runtime
 */

// TESTCASE NUMBER: 1
import kotlin.reflect.full.findAnnotation

annotation class MyAnnotation

class MyClass(val x: Int) {
    @MyAnnotation companion object {
        fun create(value: Int): MyClass = MyClass(value)
    }
}

fun box(): String {
    val instance = MyClass.create(42)
    if (instance.x != 42) return "NOK: property access"

    if (MyClass.Companion::class.findAnnotation<MyAnnotation>() == null) {
        return "NOK: missing companion annotation"
    }
    return "OK"
}
