// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, subtyping, subtyping-for-intersection-types -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Without intersection smart cast, only one supertype member set is available
 * HELPERS: checkType
 */
// TESTCASE NUMBER: 1
interface NA { fun na(): Int }
interface NB { fun nb(): String }
fun case_1(x: NA) {
    x.<!UNRESOLVED_REFERENCE!>nb<!>()
}


// TESTCASE NUMBER: 2
fun case_2(x: NB) {
    x.<!UNRESOLVED_REFERENCE!>na<!>()
}

interface NC
interface ND


// TESTCASE NUMBER: 3
fun case_3(x: NC) {
    val d: ND = <!TYPE_MISMATCH!>x<!>
}

open class Base4
class Derived4 : Base4()


// TESTCASE NUMBER: 4
fun case_4(x: Base4) {
    val d: Derived4 = <!TYPE_MISMATCH!>x<!>
}

interface NE
interface NF


// TESTCASE NUMBER: 5
fun case_5(x: NE) {
    checkSubtype<NF>(<!TYPE_MISMATCH!>x<!>)
}
