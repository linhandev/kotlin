// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, upper-and-lower-bounds, greatest-lower-bound -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Without GLB smart cast, members of other type are unavailable
 * HELPERS: checkType
 */

interface NA { fun na(): Int }
interface NB { fun nb(): String }

// TESTCASE NUMBER: 1
fun case_1(x: NA) {
    x.<!UNRESOLVED_REFERENCE!>nb<!>()
}

// TESTCASE NUMBER: 2
fun case_2(x: NB) {
    x.<!UNRESOLVED_REFERENCE!>na<!>()
}

// TESTCASE NUMBER: 3
interface NC
interface ND
fun case_3(x: NC) {
    val d: ND = <!TYPE_MISMATCH!>x<!>
}

// TESTCASE NUMBER: 4
open class Base4
class Derived4 : Base4()
fun case_4(x: Base4) {
    val d: Derived4 = <!TYPE_MISMATCH!>x<!>
}

// TESTCASE NUMBER: 5
interface P5
interface Q5
fun case_5(x: P5) {
    checkSubtype<Q5>(<!TYPE_MISMATCH!>x<!>)
}
