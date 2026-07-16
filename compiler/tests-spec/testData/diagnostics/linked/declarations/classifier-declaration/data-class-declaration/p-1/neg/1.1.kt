// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, data-class-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: data classes are closed and cannot be inherited from
 */

// TESTCASE NUMBER: 1
data class DC(val x: Int)

class Sub : <!FINAL_SUPERTYPE!>DC<!>(1)
