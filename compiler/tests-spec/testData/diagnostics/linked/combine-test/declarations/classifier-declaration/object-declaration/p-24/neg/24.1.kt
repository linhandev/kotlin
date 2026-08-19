// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, object-declaration -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: override with mismatched signature fails
 */

// TESTCASE NUMBER: 1
interface I {
    fun f(x: Int): Int
}

<!ABSTRACT_MEMBER_NOT_IMPLEMENTED!>object O<!> : I {
    <!NOTHING_TO_OVERRIDE!>override<!> fun f(x: String): Int = 1
}

fun case_1() = O.f(1)
