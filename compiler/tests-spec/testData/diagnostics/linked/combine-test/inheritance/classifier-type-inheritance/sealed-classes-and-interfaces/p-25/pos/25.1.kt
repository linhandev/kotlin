// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 25 -> sentence 25
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: exhaustive sealed when assigned to val has branch common type
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
sealed class Expr
data class Num(val n: Int) : Expr()
object Nil : Expr()

fun case_1(e: Expr) {
    val r = when (e) {
        is Num -> e.n
        is Nil -> "nil"
    }
    checkSubtype<Any>(r)
}
