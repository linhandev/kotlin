// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: top-level function without body is rejected; throw-only expression body infers Nothing return type
 */

// TESTCASE NUMBER: 1
<!NON_MEMBER_FUNCTION_NO_BODY!>fun noBody()<!>

// TESTCASE NUMBER: 2
fun <!IMPLICIT_NOTHING_RETURN_TYPE!>onlyThrow<!>() = throw RuntimeException()
