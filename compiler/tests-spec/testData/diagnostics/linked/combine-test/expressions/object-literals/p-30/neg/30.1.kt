// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, object-literals -> paragraph 30 -> sentence 30
 * PRIMARY LINKS: inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 30 -> sentence 30
 * NUMBER: 1
 * DESCRIPTION: object literal cannot implement sealed interface (anonymous local inheritor forbidden)
 */

// TESTCASE NUMBER: 1
sealed interface Marker

fun case_1(): Marker = object : <!SEALED_SUPERTYPE!>Marker<!> {}
