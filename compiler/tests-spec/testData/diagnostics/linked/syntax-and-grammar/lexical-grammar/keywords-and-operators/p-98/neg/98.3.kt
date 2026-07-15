// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 98 -> sentence 98
 * NUMBER: 3
 * DESCRIPTION: Incomplete NOT_IS check without target type causes parser error
 */

// TESTCASE NUMBER: 1
fun brokenNotIsType98(value: Any): String = value !is

<!SYNTAX!><!>fun case1(): String = "OK"
