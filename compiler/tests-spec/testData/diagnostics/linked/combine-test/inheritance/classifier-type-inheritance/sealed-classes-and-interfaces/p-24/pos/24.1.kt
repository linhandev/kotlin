// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 24 -> sentence 24
 *                type-inference, smart-casts -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: subject-less when can still use is checks on sealed values
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
sealed class Expr
data class Num(val n: Int) : Expr()
object Nil : Expr()

fun case_1(e: Expr) {
    checkSubtype<String>(when {
        e is Num -> "n"
        e is Nil -> "z"
        else -> "?"
    })
}
