// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, property-initialization -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: reading uninitialized properties is reported before first use
 */

// TESTCASE NUMBER: 1
fun readUninitializedLocal() {
    val message: String
    println(<!UNINITIALIZED_VARIABLE!>message<!>)
}

// TESTCASE NUMBER: 2
class MemberHolder {
    <!MUST_BE_INITIALIZED_OR_BE_ABSTRACT!>val value: Int<!>
}
