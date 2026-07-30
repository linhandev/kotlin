// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 50 -> sentence 50
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 50 -> sentence 50
 *                expressions, try-expressions -> paragraph 50 -> sentence 50
 * NUMBER: 1
 * DESCRIPTION: non-local return from forEach still executes finally block
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(xs: List<Int>): Int {
    try {
        xs.forEach { return 1 }
    } finally {
    }
    return 0
}

fun case_1_check() {
    checkSubtype<Int>(case_1(listOf(1)))
}
