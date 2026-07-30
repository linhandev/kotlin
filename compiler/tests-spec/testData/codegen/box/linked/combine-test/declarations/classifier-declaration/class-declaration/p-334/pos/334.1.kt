// WITH_STDLIB
// WITH_REFLECT
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 334 -> sentence 334
 * PRIMARY LINKS: annotations, annotation-declarations -> paragraph 334 -> sentence 334
 * NUMBER: 1
 * DESCRIPTION: annotation on an abstract class and the class works normally at runtime
 */

// TESTCASE NUMBER: 1
import kotlin.reflect.full.findAnnotation

annotation class MyAnnotation
@MyAnnotation abstract class MyAbstractClass(val value: Int) {
    abstract fun compute(): Int
}

class MyConcrete(value: Int) : MyAbstractClass(value) {
    override fun compute() = value
}

fun box(): String {
    val instance = MyConcrete(42)
    if (instance.compute() != 42) return "NOK: abstract class method"
    val instance2 = MyConcrete(-1)
    if (instance2.compute() != -1) return "NOK: negative value"

    if (MyAbstractClass::class.findAnnotation<MyAnnotation>() == null) return "NOK: missing abstract class annotation"
    return "OK"
}
