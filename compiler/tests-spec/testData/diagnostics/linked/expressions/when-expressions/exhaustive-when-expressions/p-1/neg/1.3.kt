// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, when-expressions, exhaustive-when-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: when on sealed A value D with only A.B and A.B.C branches reports NO_ELSE_IN_WHEN
 */

sealed class A {
    class B : A() {
        class C : A()
    }
}

class D : A()

// TESTCASE NUMBER: 1
fun case1() {
    val s: A = D()
    val x = <!NO_ELSE_IN_WHEN!>when<!> (s) {
        is A.B -> 1
        is A.B.C -> 2
    }
}
