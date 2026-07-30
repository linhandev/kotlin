// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 83 -> sentence 83
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 83 -> sentence 83
 * NUMBER: 1
 * DESCRIPTION: non-null instance == null is false; infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1

class Box

fun case1() {
    checkSubtype<Boolean>(<!SENSELESS_COMPARISON!>Box() == null<!>)
}
