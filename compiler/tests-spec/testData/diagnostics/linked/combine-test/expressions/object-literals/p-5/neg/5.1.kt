// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, object-literals -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: object literal cannot inherit final class
 */

// TESTCASE NUMBER: 1
fun case_1() = object : <!FINAL_SUPERTYPE!>String<!>() {}
