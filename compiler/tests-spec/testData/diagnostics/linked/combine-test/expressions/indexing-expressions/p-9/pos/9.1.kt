// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, indexing-expressions -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 9 -> sentence 9
 *                statements, assignments, simple-assignments -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: String index read infers Char
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    checkSubtype<Char>("ab"[0])
}
