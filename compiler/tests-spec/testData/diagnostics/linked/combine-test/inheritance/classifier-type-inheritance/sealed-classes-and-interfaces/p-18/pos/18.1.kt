// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 18 -> sentence 18
 *                type-inference, smart-casts -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: data class sealed subclass properties are accessible after is smart cast
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
sealed class Expr
data class PairVal(val a: Int, val b: Int) : Expr()

fun case_1(e: Expr) {
    checkSubtype<Int>(when (e) {
        is PairVal -> e.a + e.b
    })
}
