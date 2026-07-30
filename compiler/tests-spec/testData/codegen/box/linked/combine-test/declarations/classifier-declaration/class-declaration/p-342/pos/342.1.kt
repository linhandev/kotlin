// WITH_STDLIB
// WITH_REFLECT
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 342 -> sentence 342
 * PRIMARY LINKS: annotations, annotation-declarations -> paragraph 342 -> sentence 342
 * NUMBER: 1
 * DESCRIPTION: annotation parameter with default value and the annotated class works normally at runtime
 */

// TESTCASE NUMBER: 1
import kotlin.reflect.full.findAnnotation

annotation class MyAnnotation(val value: String = "default", val count: Int = 0)

@MyAnnotation
class MyClassA(val x: Int)

@MyAnnotation("custom")
class MyClassB(val x: Int)

@MyAnnotation("explicit", count = 5)
class MyClassC(val x: Int)

fun box(): String {
    val a = MyClassA(1)
    if (a.x != 1) return "NOK: class A default annotation param"
    val b = MyClassB(2)
    if (b.x != 2) return "NOK: class B custom annotation param"
    val c = MyClassC(3)
    if (c.x != 3) return "NOK: class C explicit annotation params"

    val annA = MyClassA::class.findAnnotation<MyAnnotation>()
        ?: return "NOK: class A annotation missing"
    if (annA.value != "default" || annA.count != 0) return "NOK: class A annotation defaults"
    val annB = MyClassB::class.findAnnotation<MyAnnotation>()
        ?: return "NOK: class B annotation missing"
    if (annB.value != "custom" || annB.count != 0) return "NOK: class B annotation values"
    val annC = MyClassC::class.findAnnotation<MyAnnotation>()
        ?: return "NOK: class C annotation missing"
    if (annC.value != "explicit" || annC.count != 5) return "NOK: class C annotation values"
    return "OK"
}
