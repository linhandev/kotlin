// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 35 -> sentence 35
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 35 -> sentence 35
 * NUMBER: 1
 * DESCRIPTION: non-inline higher-order with labeled local return infers Int for enclosing function
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun runNonInline(block: () -> Unit): Unit = block()

fun case_1(): Int {
    runNonInline { return@runNonInline }
    return 1
}

fun case_1_check() {
    checkSubtype<Int>(case_1())
}
