// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, super-forms -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: bare super in Leaf.case1 reports SUPER_IS_NOT_AN_EXPRESSION
 */

open class Base

class Leaf : Base() {
// TESTCASE NUMBER: 1
    fun case1() {
        val x = <!SUPER_IS_NOT_AN_EXPRESSION!>super<!>
    }
}
