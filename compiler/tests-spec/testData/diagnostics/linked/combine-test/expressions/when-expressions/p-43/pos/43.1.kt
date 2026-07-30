// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST -USELESS_IS_CHECK
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 43 -> sentence 43
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 43 -> sentence 43
 *                inheritance, classifier-type-inheritance, open-classes -> paragraph 43 -> sentence 43
 * NUMBER: 1
 * DESCRIPTION: when expression with supertype is branch before subtype is branch type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
open class A
class B(val v: Int) : A()

fun case1() {
    val x: A = B(1)
    checkSubtype<Int>(when (x) {
        is A -> 0
        is B -> x.v
        else -> -1
    })
}
