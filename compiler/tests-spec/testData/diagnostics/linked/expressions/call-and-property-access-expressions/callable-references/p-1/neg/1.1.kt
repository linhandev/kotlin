// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, call-and-property-access-expressions, callable-references -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: A::a is ambiguous between property a and function a()
 */

class A {
    val a: Int = 42
    fun a(): String = "x"
}

// TESTCASE NUMBER: 1
fun case1() {
    val ref = A::<!OVERLOAD_RESOLUTION_AMBIGUITY!>a<!>
}
