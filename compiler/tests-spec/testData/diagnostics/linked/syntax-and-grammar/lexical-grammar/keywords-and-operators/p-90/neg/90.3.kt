// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 90 -> sentence 90
 * NUMBER: 3
 * DESCRIPTION: Incomplete while loop WHILE without body causes parser error
 */

// TESTCASE NUMBER: 1
fun brokenWhileBody90(): String = while (true)

<!SYNTAX!><!>fun case1(): String {
    return "OK"
}
