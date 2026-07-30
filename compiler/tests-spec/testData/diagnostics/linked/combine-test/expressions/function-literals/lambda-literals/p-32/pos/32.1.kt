// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 32 -> sentence 32
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 32 -> sentence 32
 * NUMBER: 1
 * DESCRIPTION: inline map non-local return with value when element equals zero
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(xs: List<Int>): Int {
    return xs.map { if (it == 0) return 0 else it * 2 }.first()
}

fun case_1_check() {
    checkSubtype<Int>(case_1(listOf(1, 0, 2)))
}
