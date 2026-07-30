// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 55 -> sentence 55
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 55 -> sentence 55
 * NUMBER: 1
 * DESCRIPTION: nested inline invoke allows non-local return to named function
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
inline fun invoke(block: () -> Unit): Unit = block()

fun case_1(): Int {
    invoke { invoke { return 1 } }
    return 0
}

fun case_1_check() {
    checkSubtype<Int>(case_1())
}
