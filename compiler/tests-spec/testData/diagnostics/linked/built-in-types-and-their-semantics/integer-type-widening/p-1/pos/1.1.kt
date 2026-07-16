// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, integer-type-widening -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: overload-resolution, choosing-the-most-specific-candidate-from-the-overload-candidate-set, algorithm-of-msc-selection -> paragraph 12 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: integer literal overload resolution prefers kotlin.Int over kotlin.Short
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun foo(value: Int): String = "int" // (1.1)
fun foo(value: Short): Unit = TODO() // (1.2)

fun case_1() {
    checkSubtype<String>(<!DEBUG_INFO_CALL("fqName: foo; typeCall: function")!>foo(2)<!>)
    checkSubtype<String>(<!DEBUG_INFO_EXPRESSION_TYPE("kotlin.String")!>foo(2)<!>)
}


// TESTCASE NUMBER: 2
class Case2 {
    fun foo(value: Int): String = "int" // (1.1)
    fun foo(value: Short): Unit = TODO() // (1.2)

    fun case() {
        checkSubtype<String>(this.<!DEBUG_INFO_CALL("fqName: Case2.foo; typeCall: function")!>foo(2)<!>)
        checkSubtype<String>(<!DEBUG_INFO_EXPRESSION_TYPE("kotlin.String")!>this.foo(2)<!>)
    }
}
