// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 39 -> sentence 39
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 39 -> sentence 39
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 39 -> sentence 39
 * NUMBER: 1
 * DESCRIPTION: trailing lambda into custom inline allows non-local return
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
inline fun <R> applyBlock(block: () -> R): R = block()

fun case_1(): Int {
    applyBlock { return 3 }
}

fun case_1_check() {
    checkSubtype<Int>(case_1())
}
