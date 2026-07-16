// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.any -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: equals must override kotlin.Any.equals(other: Any?): Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Case1 {
    <!NOTHING_TO_OVERRIDE!>override<!> fun equals(other: Case1): Boolean = false
}


// TESTCASE NUMBER: 2
class Case2 {
    <!NOTHING_TO_OVERRIDE!>override<!> fun equals(other: String): Boolean = false
}


// TESTCASE NUMBER: 3
class Case3 {
    <!NOTHING_TO_OVERRIDE!>override<!> fun equals(other: Any): Boolean = false
}


// TESTCASE NUMBER: 4
class Case4 {
    <!NOTHING_TO_OVERRIDE!>override<!> fun equals(): Boolean = false
}


// TESTCASE NUMBER: 5
open class Case5Base
class Case5 : Case5Base() {
    <!NOTHING_TO_OVERRIDE!>override<!> fun equals(other: Case5Base?): Boolean = false
}
