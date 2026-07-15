// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 85 -> sentence 85
 * NUMBER: 3
 * DESCRIPTION: Incomplete try statement TRY without block causes parser error
 */

// TESTCASE NUMBER: 1
fun brokenTryBody85(): String {
    try
    <!SYNTAX!><!>return "OK"
}<!SYNTAX!><!>

fun case1(): String = "OK"
