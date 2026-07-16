// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 35 -> sentence 35
 * NUMBER: 2
 * DESCRIPTION: Block comment between @ and annotation name breaks AT_NO_WS @/*c
 */
// TESTCASE NUMBER: 1

Deprecated
 */

<!SYNTAX!>@<!>/*no-ws*/<!SYNTAX!>Deprecated<!><!SYNTAX!>(<!><!SYNTAX!>"<!><!SYNTAX!>legacy<!><!SYNTAX!>"<!><!SYNTAX!>)<!>
class Broken

fun case1(): String {
    return "OK"
}
