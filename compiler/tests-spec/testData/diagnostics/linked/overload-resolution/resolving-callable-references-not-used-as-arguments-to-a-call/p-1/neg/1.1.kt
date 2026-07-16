// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, resolving-callable-references-not-used-as-arguments-to-a-call -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: function and property with same name in OCS produce overload ambiguity for ::foo
 */

fun foo1161N(): Int = 1
val foo1161N = 2

// TESTCASE NUMBER: 1
fun case_1() {
    val ref = ::<!OVERLOAD_RESOLUTION_AMBIGUITY!>foo1161N<!>
}
