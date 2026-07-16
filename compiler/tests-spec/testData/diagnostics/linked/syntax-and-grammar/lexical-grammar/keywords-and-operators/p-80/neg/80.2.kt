// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 80 -> sentence 80
 * NUMBER: 2
 * DESCRIPTION: TYPEOF token used as unescaped function name causes compile error
 */

// TESTCASE NUMBER: 1
<!FUNCTION_DECLARATION_WITH_NO_NAME!>fun <!SYNTAX!>typeof<!>(): String<!> = "OK"

fun case1(): String {
    <!RETURN_TYPE_MISMATCH!>return<!> <!SYNTAX!>typeof()<!>
}
