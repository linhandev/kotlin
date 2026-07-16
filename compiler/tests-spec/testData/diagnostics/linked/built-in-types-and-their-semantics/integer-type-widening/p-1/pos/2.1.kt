// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, integer-type-widening -> paragraph 1 -> sentence 2
 * PRIMARY LINKS: overload-resolution, choosing-the-most-specific-candidate-from-the-overload-candidate-set, algorithm-of-msc-selection -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: integer literal overload resolution prefers kotlin.Short over kotlin.Byte
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Case1 {
    fun foo(x: Short): String = TODO() // (1.1)
    fun foo(x: Byte): Unit = TODO() // (1.2)
}

fun case_1(case: Case1) {
    checkSubtype<String>(case.<!DEBUG_INFO_CALL("fqName: Case1.foo; typeCall: function")!>foo(1)<!>)
    checkSubtype<String>(<!DEBUG_INFO_EXPRESSION_TYPE("kotlin.String")!>case.foo(1)<!>)
}
