// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, resolving-property-access -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: val property selected for assignment is rejected as not assignable
 */

class ReadOnly1145N {
    val locked1145N: Int = 1
}

// TESTCASE NUMBER: 1
fun case_1(ro: ReadOnly1145N) {
    <!VAL_REASSIGNMENT!>ro.locked1145N<!> = 2
}
