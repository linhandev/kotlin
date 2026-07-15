// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, integer-type-widening -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: overload-resolution, choosing-the-most-specific-candidate-from-the-overload-candidate-set, algorithm-of-msc-selection -> paragraph 12 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: integer literal overload resolution prefers kotlin.Int over kotlin.Long, kotlin.Short and kotlin.Byte
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Case1 {
    fun foo(x: Int): String = TODO() // (1.1)
    fun foo(x: Long): Unit = TODO() // (1.2)
    fun foo(x: Short): Unit = TODO() // (1.3)
    fun foo(x: Byte): Unit = TODO() // (1.4)
}

fun case_1(case: Case1) {
    checkSubtype<String>(case.<!DEBUG_INFO_CALL("fqName: Case1.foo; typeCall: function")!>foo(1)<!>)
    checkSubtype<String>(<!DEBUG_INFO_EXPRESSION_TYPE("kotlin.String")!>case.foo(1)<!>)
}


// TESTCASE NUMBER: 2
class Case2 {
    fun foo(x: Int, y: Int): String = TODO() // (1.1)
    fun foo(x: Long, y: Int): Unit = TODO() // (1.2)
    fun foo(x: Short, y: Int): Unit = TODO() // (1.3)
    fun foo(x: Byte, y: Int): Unit = TODO() // (1.4)
}

fun case_2(case: Case2) {
    checkSubtype<String>(case.<!DEBUG_INFO_CALL("fqName: Case2.foo; typeCall: function")!>foo(1, 2)<!>)
    checkSubtype<String>(<!DEBUG_INFO_EXPRESSION_TYPE("kotlin.String")!>case.foo(1, 2)<!>)
}
