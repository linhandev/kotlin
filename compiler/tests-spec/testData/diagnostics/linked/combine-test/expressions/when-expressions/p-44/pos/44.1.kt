// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 44 -> sentence 44
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 44 -> sentence 44
 *                inheritance, classifier-type-inheritance, open-classes -> paragraph 44 -> sentence 44
 *                type-inference, smart-casts -> paragraph 44 -> sentence 44
 * NUMBER: 1
 * DESCRIPTION: when on Base with is B before is A infers Int; both is checks are useful
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
open class Base
open class A : Base()
class B(val v: Int) : A()

fun case1() {
    val x: Base = B(5)
    checkSubtype<Int>(when (x) {
        is B -> x.v
        is A -> 0
        else -> -1
    })
}

// TESTCASE NUMBER: 2
fun case2() {
    val x: Base = A()
    checkSubtype<Int>(when (x) {
        is B -> x.v
        is A -> 0
        else -> -1
    })
}

// TESTCASE NUMBER: 3
fun case3() {
    val x: Base = Base()
    checkSubtype<Int>(when (x) {
        is B -> x.v
        is A -> 0
        else -> -1
    })
}
