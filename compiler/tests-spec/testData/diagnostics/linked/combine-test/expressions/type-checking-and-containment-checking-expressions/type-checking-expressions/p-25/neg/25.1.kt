// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: declarations, declarations-with-type-parameters, reified-type-parameters -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: reified type parameter can only be declared in inline functions
 */

// TESTCASE NUMBER: 1
fun <<!REIFIED_TYPE_PARAMETER_NO_INLINE!>reified<!> T> case_1(value: Any?): Boolean = TODO()
