// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, property-declaration-scopes -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: accessor bodies cannot access members outside their permitted scopes
 */

// TESTCASE NUMBER: 1
class C {
    private fun secret(): Int = 1
}

var exposed: Int = 0
    get() = C().<!INVISIBLE_MEMBER!>secret<!>()

// TESTCASE NUMBER: 2
class Box {
    private val hidden = 42
}

val Box.visible: Int
    get() = <!INVISIBLE_MEMBER!>hidden<!>
