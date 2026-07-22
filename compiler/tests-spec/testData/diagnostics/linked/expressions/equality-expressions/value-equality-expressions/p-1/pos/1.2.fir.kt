// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, equality-expressions, value-equality-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * UNEXPECTED BEHAVIOUR
 * DESCRIPTION: value equality on definitely distinct unrelated types is allowed by FIR
 */

// TESTCASE NUMBER: 1
class A
class B
fun case1() {
    val x = A() == B()
}
