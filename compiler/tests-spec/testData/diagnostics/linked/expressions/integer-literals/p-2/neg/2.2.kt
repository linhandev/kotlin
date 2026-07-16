// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, integer-literals -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: hexadecimal literal 0xFF_ with underscore after last digit reports ILLEGAL_UNDERSCORE
 */

// TESTCASE NUMBER: 1
fun case1() {
    val x = <!ILLEGAL_UNDERSCORE!>0xFF_<!>
}
