// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 7 -> sentence 7
 * NUMBER: 2
 * DESCRIPTION: Invalid FloatLiteral 1.f with missing fraction DecDigits before suffix
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    val value = 1.<!UNRESOLVED_REFERENCE!>f<!>
<!NO_RETURN_IN_FUNCTION_WITH_BLOCK_BODY!>}<!>
