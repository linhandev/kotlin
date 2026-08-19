// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 11 -> sentence 11
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 11 -> sentence 11
 *                type-system, introduction-1 -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: nullable sealed when with null and subclass branches is exhaustive
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
sealed class Expr
data class Num(val n: Int) : Expr()
object Nil : Expr()

fun case_1(e: Expr?) {
    checkSubtype<Int>(when (e) {
        is Num -> e.n
        is Nil -> 0
        null -> -1
    })
}
