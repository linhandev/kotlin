// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 16 -> sentence 16
 *                type-system, introduction-1 -> paragraph 16 -> sentence 16
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: when expression with nullable sealed class subject missing null branch and without else is not exhaustive
 */

// TESTCASE NUMBER: 1
sealed class Result {
    class Ok(val value: String) : Result()
    object Err : Result()
}

fun test(r: Result?): Int = <!NO_ELSE_IN_WHEN!>when<!>(r) {
    is Result.Ok -> r.value.length
    Result.Err -> -1
}
