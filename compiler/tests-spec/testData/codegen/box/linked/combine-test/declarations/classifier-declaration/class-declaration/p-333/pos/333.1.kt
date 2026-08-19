// WITH_STDLIB
// WITH_REFLECT
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 333 -> sentence 333
 * PRIMARY LINKS: annotations, annotation-declarations -> paragraph 333 -> sentence 333
 * NUMBER: 1
 * DESCRIPTION: annotation on an interface and the interface works normally at runtime
 */

// TESTCASE NUMBER: 1
import kotlin.reflect.full.findAnnotation

annotation class MyAnnotation
@MyAnnotation interface MyInterface {
    fun compute(): Int
}

class MyImpl(val value: Int) : MyInterface {
    override fun compute() = value
}

fun box(): String {
    val instance: MyInterface = MyImpl(42)
    if (instance.compute() != 42) return "NOK: interface method"
    val instance2 = MyImpl(-1)
    if (instance2.compute() != -1) return "NOK: negative value"

    if (MyInterface::class.findAnnotation<MyAnnotation>() == null) return "NOK: missing interface annotation"
    return "OK"
}
