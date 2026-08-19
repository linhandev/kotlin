// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 53 -> sentence 53
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 53 -> sentence 53
 * NUMBER: 1
 * DESCRIPTION: for-loop return exits enclosing function as control contrast to lambda
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(xs: List<Int>): Int {
    for (x in xs) {
        if (x < 0) return -1
    }
    return 0
}

fun case_1_check() {
    checkSubtype<Int>(case_1(listOf(1, -2)))
}
