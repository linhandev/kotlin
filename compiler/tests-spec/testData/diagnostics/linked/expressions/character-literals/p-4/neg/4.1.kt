// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, character-literals -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: \u escape with one to three hex digits and \u1wf with non-hex digit w report ILLEGAL_ESCAPE
 */

// TESTCASE NUMBER: 1
fun case1() {
    val c0 = '<!ILLEGAL_ESCAPE!>\u<!>'
    val c1 = '<!ILLEGAL_ESCAPE!>\uf<!>'
    val c2 = '<!ILLEGAL_ESCAPE!>\u1f<!>'
}

// TESTCASE NUMBER: 2
fun case2() {
    val c3 = '<!ILLEGAL_ESCAPE!>\u1wf<!>'
}
