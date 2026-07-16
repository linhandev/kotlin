// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, when-expressions, exhaustive-when-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: when (Derived1()) with only is Derived1 branch missing Derived2 reports NO_ELSE_IN_WHEN
 */

sealed class Base
class Derived1 : Base()
class Derived2 : Base()

// TESTCASE NUMBER: 1
fun case1() {
    val s: Base = Derived1()
    val x = <!NO_ELSE_IN_WHEN!>when<!>(s) {
        is Derived1 -> 1
    }
}
