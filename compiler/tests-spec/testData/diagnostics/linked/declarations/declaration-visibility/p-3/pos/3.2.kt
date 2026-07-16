// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, declaration-visibility -> paragraph 3 -> sentence 3
 * NUMBER: 2
 * DESCRIPTION: private member accessible via this in same class
 */

// TESTCASE NUMBER: 1
class Node(private val id: Int) {
    fun same(other: Node): Boolean = this.id == other.id
}
