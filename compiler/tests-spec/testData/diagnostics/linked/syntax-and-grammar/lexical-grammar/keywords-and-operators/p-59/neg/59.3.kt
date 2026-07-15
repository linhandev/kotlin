// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 59 -> sentence 59
 * NUMBER: 3
 * DESCRIPTION: Incomplete property getter GET without parentheses causes parser error
 */

// TESTCASE NUMBER: 1
<!MUST_BE_INITIALIZED!>val incompleteGet59: Int<!>
    get
    <!SYNTAX!>=<!> <!SYNTAX!>1<!>

fun case1(): String {
    return "OK"
}
