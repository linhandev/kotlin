// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 45 -> sentence 45
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 45 -> sentence 45
 * NUMBER: 1
 * DESCRIPTION: anonymous function passed to inline still returns only from anonymous function
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
inline fun invokeRun(block: () -> Unit): Unit = block()

fun case_1(): Int {
    invokeRun(fun() { return })
    return 1
}

fun case_1_check() {
    checkSubtype<Int>(case_1())
}
