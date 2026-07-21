// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, declaration-visibility -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: private-to-this members are not visible on other instances of the same class
 */

// TESTCASE NUMBER: 1
class Marker

class Holder<in T> {
    private val value: T = TODO()

    fun readOther(other: Holder<Marker>): Any? = other.<!INVISIBLE_REFERENCE!>value<!>
}
