// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 44 -> sentence 44
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 44 -> sentence 44
 *                inheritance, classifier-type-inheritance, open-classes -> paragraph 44 -> sentence 44
 *                type-inference, smart-casts -> paragraph 44 -> sentence 44
 * NUMBER: 1
 * DESCRIPTION: when on Base matches is B first, then is A as parent fallback (not subject-type is A)
 */

// TESTCASE NUMBER: 1
open class Base
open class A : Base()
class B(val v: Int) : A()

fun test(x: Base): Int = when (x) {
    is B -> x.v
    is A -> 0
    else -> -1
}

fun box(): String {
    // subtype-first: B takes is B (return v); plain A takes parent is A (contrast p-43)
    if (test(B(5)) != 5) return "NOK"
    if (test(A()) != 0) return "NOK"
    if (test(Base()) != -1) return "NOK"
    return "OK"
}
