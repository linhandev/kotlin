// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: Standalone RCURL as statement causes parser error
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    <!NO_RETURN_IN_FUNCTION_WITH_BLOCK_BODY!>}<!>
    <!SYNTAX!>return<!> <!SYNTAX!>"<!><!SYNTAX!>OK<!><!SYNTAX!>"<!>
<!SYNTAX!>}<!>
