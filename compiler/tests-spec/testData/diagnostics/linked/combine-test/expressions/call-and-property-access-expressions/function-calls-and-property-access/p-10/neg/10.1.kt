// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 10 -> sentence 10
 *                overload-resolution, building-the-overload-candidate-set, call-with-trailing-lambda-expressions -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: stored lambda property cannot accept an extra trailing lambda argument
 */

// TESTCASE NUMBER: 1
class Handler {
    val block: () -> Unit = {}
}

fun test(h: Handler) = h.block<!TOO_MANY_ARGUMENTS!>{ }<!>
