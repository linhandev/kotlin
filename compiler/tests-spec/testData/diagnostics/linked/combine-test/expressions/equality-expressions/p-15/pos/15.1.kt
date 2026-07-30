// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: expressions, equality-expressions, reference-equality-expressions -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: string literal === infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    checkSubtype<Boolean>("a" === "a")
}
