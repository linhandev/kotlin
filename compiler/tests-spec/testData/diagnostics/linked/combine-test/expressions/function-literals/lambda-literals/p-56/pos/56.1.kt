// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 56 -> sentence 56
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 56 -> sentence 56
 * NUMBER: 1
 * DESCRIPTION: inline guard with condition allows non-local return from lambda
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
inline fun guard(cond: Boolean, block: () -> Unit): Unit {
    if (cond) block()
}

fun case_1(): Int {
    guard(true) { return 4 }
    return 0
}

fun case_1_check() {
    checkSubtype<Int>(case_1())
}
