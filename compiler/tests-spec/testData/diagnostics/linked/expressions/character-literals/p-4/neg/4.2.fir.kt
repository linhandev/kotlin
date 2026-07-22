// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, character-literals -> paragraph 4 -> sentence 4
 * NUMBER: 2
 * DESCRIPTION: \u000g and \u000G with non-hex digits and \u1wF2f with five hex digits report ILLEGAL_ESCAPE
 */

// TESTCASE NUMBER: 1
fun case1() {
    val c1 = <!ILLEGAL_ESCAPE!>'\u000g'<!>
    val c2 = <!ILLEGAL_ESCAPE!>'\u000G'<!>
}

// TESTCASE NUMBER: 2
fun case2() {
    val c3 = <!ILLEGAL_ESCAPE!>'\u1wF2f'<!>
}
