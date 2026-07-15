// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 61 -> sentence 61
 * NUMBER: 3
 * DESCRIPTION: Incomplete receiver annotation @receiver: missing annotation name causes parser error
 */

// TESTCASE NUMBER: 1
<!FUNCTION_DECLARATION_WITH_NO_NAME!>fun <!SYNTAX!>@receiver: <!DEBUG_INFO_MISSING_UNRESOLVED!>String<!>.<!DEBUG_INFO_MISSING_UNRESOLVED!>broken<!>()<!><!SYNTAX!><!>: String<!> = <!NO_THIS!>this<!>

fun case1(): String {
    return "OK"
}
