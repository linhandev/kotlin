// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 47 -> sentence 47
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 47 -> sentence 47
 * NUMBER: 1
 * DESCRIPTION: non-local return from let lambda on nullable receiver
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(x: Int?): Int {
    x?.let { if (it < 0) return -1 }
    return 0
}

fun case_1_check() {
    checkSubtype<Int>(case_1(-5))
}
