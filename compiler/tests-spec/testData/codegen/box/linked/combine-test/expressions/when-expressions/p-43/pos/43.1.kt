// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 43 -> sentence 43
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 43 -> sentence 43
 *                inheritance, classifier-type-inheritance, open-classes -> paragraph 43 -> sentence 43
 * NUMBER: 1
 * DESCRIPTION: when expression with supertype is branch before subtype is branch matches supertype first
 */

// TESTCASE NUMBER: 1
open class A
class B(val v: Int) : A()

fun test(x: A): Int = when (x) {
    is A -> 0
    is B -> x.v
    else -> -1
}

fun box(): String {
    if (test(B(5)) != 0) return "NOK"
    if (test(A()) != 0) return "NOK"
    return "OK"
}
