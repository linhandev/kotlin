// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 105 -> sentence 105
 * NUMBER: 2
 * DESCRIPTION: Space inside INTERNAL token as inter nal breaks modifier lexeme
 */

// TESTCASE NUMBER: 1
<!SYNTAX!>inter<!> <!SYNTAX!>nal<!> class BrokenInternal105

fun case1(): String = "OK"
