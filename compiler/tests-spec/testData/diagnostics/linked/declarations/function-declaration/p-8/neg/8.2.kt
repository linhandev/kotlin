// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 8 -> sentence 8
 * NUMBER: 2
 * DESCRIPTION: duplicate named argument binding
 */

// TESTCASE NUMBER: 1
fun foo(a: Int, b: String) {}

fun duplicateBinding() {
    foo(a = 1, <!ARGUMENT_PASSED_TWICE!>a<!> = 2, b = "x")
}
