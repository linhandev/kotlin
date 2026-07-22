// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, assignments -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: Operator assignment to read-only property h.n reports VAL_REASSIGNMENT
 */

// TESTCASE NUMBER: 1
class Holder {
    val n = 1
}

fun case1() {
    val h = Holder()
    <!VAL_REASSIGNMENT!>h.n<!> += 1
}
