// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, object-literals -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: override signature mismatch in object literal fails
 */

// TESTCASE NUMBER: 1
interface I {
    fun f(x: Int): Int
}

fun case_1() = <!ABSTRACT_MEMBER_NOT_IMPLEMENTED!>object<!> : I {
    <!NOTHING_TO_OVERRIDE!>override<!> fun f(x: String): Int = 1
}
