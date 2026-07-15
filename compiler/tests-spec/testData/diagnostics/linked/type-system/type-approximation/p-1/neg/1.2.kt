// FIR_IDENTICAL
// DIAGNOSTICS: -IMPLICIT_CAST_TO_ANY -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-approximation -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Approximated type does not expose non-denotable intersection directly
 * HELPERS: checkType
 */
// TESTCASE NUMBER: 1
interface GA
interface GB
fun case_1(x: GA) {
    val b: GB = <!TYPE_MISMATCH!>x<!>
}


// TESTCASE NUMBER: 2
fun case_2(x: GB) {
    val a: GA = <!TYPE_MISMATCH!>x<!>
}

interface GC


// TESTCASE NUMBER: 3
fun case_3(x: GC) {
    x.<!UNRESOLVED_REFERENCE!>unknown<!>()
}
