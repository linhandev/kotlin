// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 4 -> sentence 4
 *                type-inference, smart-casts -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: is branch smart-casts sealed subject to data class subclass
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
sealed class Expr
data class Num(val n: Int) : Expr()
object Other : Expr()

fun case_1(e: Expr) {
    checkSubtype<Int>(when (e) {
        is Num -> e.n
        else -> 0
    })
}
