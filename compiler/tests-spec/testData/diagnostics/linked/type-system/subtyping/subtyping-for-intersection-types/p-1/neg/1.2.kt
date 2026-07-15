// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, subtyping, subtyping-for-intersection-types -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Single supertype does not imply intersection with unrelated type
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

// TESTCASE NUMBER: 3
fun case_3() {
    val i: Int = 1
    val s: String = <!TYPE_MISMATCH!>i<!>
}

open class CX
class DX : CX()

// TESTCASE NUMBER: 4
fun case_4(x: CX) {
    checkSubtype<DX>(<!TYPE_MISMATCH!>x<!>)
}

interface G1
interface G2
class OnlyG1 : G1
