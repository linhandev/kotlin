// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -NO_VALUE_FOR_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: unknown named parameter, duplicate named argument, and positional followed by duplicate name are rejected
 */

// TESTCASE NUMBER: 1
fun foo(a: Int) {}

fun unknownName() {
    foo(<!NAMED_PARAMETER_NOT_FOUND!>b<!> = 1)
}

// TESTCASE NUMBER: 2
fun duplicateNamed() {
    foo(a = 1, <!ARGUMENT_PASSED_TWICE!>a<!> = 2)
}

// TESTCASE NUMBER: 3
fun duplicateMixed() {
    foo(1, <!ARGUMENT_PASSED_TWICE!>a<!> = 2)
}
