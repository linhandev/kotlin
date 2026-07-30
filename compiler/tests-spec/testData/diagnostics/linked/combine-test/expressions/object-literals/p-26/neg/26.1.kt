// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, object-literals -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: object literal implementing interface must use override
 */

// TESTCASE NUMBER: 1
interface I {
    fun f(): Int
}

fun case_1() = object : I {
    fun <!VIRTUAL_MEMBER_HIDDEN!>f<!>(): Int = 1
}
