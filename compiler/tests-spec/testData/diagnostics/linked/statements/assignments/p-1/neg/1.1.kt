// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, assignments -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Reassignment of val array reference reports VAL_REASSIGNMENT
 */

// TESTCASE NUMBER: 1
fun case1() {
    val items = arrayOf(1, 2, 3)
    <!VAL_REASSIGNMENT!>items<!> = arrayOf(4, 5, 6)
}
