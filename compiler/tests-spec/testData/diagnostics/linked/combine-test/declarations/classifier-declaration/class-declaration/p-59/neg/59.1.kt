// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 59 -> sentence 59
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 59 -> sentence 59
 * NUMBER: 1
 * DESCRIPTION: contradictory class upper bounds Number and String rejected
 */

// TESTCASE NUMBER: 1
class Bad<<!CONFLICTING_UPPER_BOUNDS!>T<!>> where T : Number, T : <!FINAL_UPPER_BOUND, ONLY_ONE_CLASS_BOUND_ALLOWED!>String<!>
