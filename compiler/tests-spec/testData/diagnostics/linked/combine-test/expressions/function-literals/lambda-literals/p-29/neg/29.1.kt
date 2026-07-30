// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -UNUSED_DESTRUCTURED_PARAMETER_ENTRY
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 29 -> sentence 29
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 29 -> sentence 29
 *                expressions, jump-expressions, return-expressions -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: non-inline higher-order forbids bare return in destructuring lambda
 */

// TESTCASE NUMBER: 1
fun each(xs: List<Pair<Int, Int>>, block: (Pair<Int, Int>) -> Unit) = xs.forEach(block)

fun case_1() =
    each(listOf(1 to 2)) { (a, b) -> <!RETURN_NOT_ALLOWED!>return<!> }
