// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, additive-expressions -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: String plus Any uses built-in concatenation
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(): String = "x" + 1

fun case_1_check() {
    checkSubtype<String>(case_1())
}
