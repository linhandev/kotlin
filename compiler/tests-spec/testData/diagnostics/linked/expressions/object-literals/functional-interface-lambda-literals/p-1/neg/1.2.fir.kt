// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, object-literals, functional-interface-lambda-literals -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: NotFI { 1 } SAM syntax on non-functional interface reports RESOLUTION_TO_CLASSIFIER
 */

interface NotFI {
    fun bar(): Int
}

// TESTCASE NUMBER: 1
fun case1() {
    val bad = <!INTERFACE_AS_FUNCTION!>NotFI<!> { 1 }
}
