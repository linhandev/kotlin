// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, intersection-types -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Intersection type members are not accessible without appropriate smart casts
 * HELPERS: checkType
 */

interface IX {
    fun x(): Int
}

interface IY {
    fun y(): String
}

// TESTCASE NUMBER: 1
fun case_1(v: Any) {
    if (v is IX) {
        v.<!UNRESOLVED_REFERENCE!>y<!>()
    }
}


// TESTCASE NUMBER: 2
fun case_2(v: Any) {
    if (v is IY) {
        v.<!UNRESOLVED_REFERENCE!>x<!>()
    }
}


// TESTCASE NUMBER: 3
fun case_3(v: Any) {
    if (v is IX) {
        val s: String = <!TYPE_MISMATCH!>v<!>
    }
}


// TESTCASE NUMBER: 4
fun case_4(v: Any) {
    if (v is IY) {
        val i: Int = <!TYPE_MISMATCH!>v<!>
    }
}


// TESTCASE NUMBER: 5
fun case_5(v: Any) {
    if (v is IX) {
        checkSubtype<IY>(<!TYPE_MISMATCH!>v<!>)
    }
}
