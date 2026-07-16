// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: operator-overloading, destructuring-declarations -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: destructuring with more placeholders than component functions reports COMPONENT_FUNCTION_MISSING
 */

// TESTCASE NUMBER: 1
class A911N {
    operator fun component1() = 1
    operator fun component2() = 2
}

fun case_1() {
    val (_, _, _) = <!COMPONENT_FUNCTION_MISSING!>A911N()<!>
}
