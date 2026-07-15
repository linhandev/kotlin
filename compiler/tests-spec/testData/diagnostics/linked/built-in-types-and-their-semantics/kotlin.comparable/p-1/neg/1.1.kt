// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.comparable -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: compareTo override must match kotlin.Comparable member signature
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
<!ABSTRACT_MEMBER_NOT_IMPLEMENTED!>class Case1<!> : Comparable<Int> {
    <!NOTHING_TO_OVERRIDE!>override<!> fun compareTo(other: String): Int = 0
}


// TESTCASE NUMBER: 2
class Case2 : Comparable<Int> {
    override fun compareTo(other: Int): <!RETURN_TYPE_MISMATCH_ON_OVERRIDE!>Boolean<!> = true
}


// TESTCASE NUMBER: 3
<!ABSTRACT_MEMBER_NOT_IMPLEMENTED!>class Case3<!> : Comparable<String> {
    <!NOTHING_TO_OVERRIDE!>override<!> fun compareTo(other: Int): Int = 0
}
