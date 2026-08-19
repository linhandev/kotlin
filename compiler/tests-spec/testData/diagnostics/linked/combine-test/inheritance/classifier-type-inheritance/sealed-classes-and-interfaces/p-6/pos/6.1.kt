// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 6 -> sentence 6
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: object subclass requires its own is branch for exhaustiveness
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
sealed class Expr
data class Num(val n: Int) : Expr()
object Nil : Expr()

fun case_1(e: Expr) {
    checkSubtype<String>(when (e) {
        is Num -> "n"
        is Nil -> "nil"
    })
}
