// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, equality-expressions, reference-equality-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: A() === B() on unrelated classes reports EQUALITY_NOT_APPLICABLE
 */

class A
class B
// TESTCASE NUMBER: 1
fun case1() {
    val x = <!EQUALITY_NOT_APPLICABLE!>A() === B()<!>
}
