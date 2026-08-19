// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 46 -> sentence 46
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 46 -> sentence 46
 * NUMBER: 1
 * DESCRIPTION: lambda non-local return versus anonymous function local return under inline
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
inline fun invokeRun(block: () -> Unit): Unit = block()

fun case_1_lambda(): Int {
    invokeRun { return 2 }
    return 1
}

fun case_1_anon(): Int {
    invokeRun(fun() { return })
    return 1
}

fun case_1_check() {
    checkSubtype<Int>(case_1_lambda())
    checkSubtype<Int>(case_1_anon())
}
