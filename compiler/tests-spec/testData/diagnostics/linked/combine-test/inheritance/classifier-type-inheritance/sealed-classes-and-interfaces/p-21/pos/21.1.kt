// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 21 -> sentence 21
 *                type-inference, smart-casts -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: !is concrete subclass then else branch smart-casts to remaining sealed type
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
sealed class Expr
data class Num(val n: Int) : Expr()
data class Add(val l: Expr, val r: Expr) : Expr()

fun case_1(e: Expr) {
    checkSubtype<Int>(when (e) {
        !is Num -> 0
        else -> e.n
    })
}
