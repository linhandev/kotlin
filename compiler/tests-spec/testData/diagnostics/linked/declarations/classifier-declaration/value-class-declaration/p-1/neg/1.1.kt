// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_PARAMETER -INLINE_CLASS_DEPRECATED
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, value-class-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: value class must have exactly one final val constructor parameter; multiple parameters or var parameter are rejected
 */

// TESTCASE NUMBER: 1
@JvmInline
value class A1<!INLINE_CLASS_CONSTRUCTOR_WRONG_PARAMETERS_SIZE!>(val x: Int, val y: Int)<!>

// TESTCASE NUMBER: 2
@JvmInline
value class A2(<!VALUE_CLASS_CONSTRUCTOR_NOT_FINAL_READ_ONLY_PARAMETER!>var x: Int<!>)
