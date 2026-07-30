// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 51 -> sentence 51
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 51 -> sentence 51
 * NUMBER: 1
 * DESCRIPTION: non-local return from onEach extension lambda
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(xs: List<Int>): Int {
    xs.onEach { if (it == 0) return -1 }
    return 1
}

fun case_1_check() {
    checkSubtype<Int>(case_1(listOf(1, 0, 2)))
}
