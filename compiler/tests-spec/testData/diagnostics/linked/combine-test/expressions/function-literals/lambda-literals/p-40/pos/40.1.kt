// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 40 -> sentence 40
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 40 -> sentence 40
 * NUMBER: 1
 * DESCRIPTION: nested inline higher-order non-local return reaches outermost named function
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
inline fun outer(block: () -> Unit): Unit = block()
inline fun inner(block: () -> Unit): Unit = outer(block)

fun case_1(): Int {
    inner { return 5 }
    return 0
}

fun case_1_check() {
    checkSubtype<Int>(case_1())
}
