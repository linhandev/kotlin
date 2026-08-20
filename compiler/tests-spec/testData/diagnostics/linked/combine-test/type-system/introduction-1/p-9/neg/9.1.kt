// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: type-system, type-kinds, classifier-types, parameterized-classifier-types -> paragraph 9 -> sentence 9
 *                type-system, type-kinds, type-parameters, use-site-variance -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: star-projected MutableList<*> cannot add a concrete element
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val xs: MutableList<*> = mutableListOf(1)
    xs.add(<!CONSTANT_EXPECTED_TYPE_MISMATCH!>1<!>)
}
