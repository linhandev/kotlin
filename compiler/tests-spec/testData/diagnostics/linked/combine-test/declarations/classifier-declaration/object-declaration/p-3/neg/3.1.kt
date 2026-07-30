// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, object-declaration -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: object implementing interface members must use override
 */

// TESTCASE NUMBER: 1
interface I {
    fun f(): Int
}

object O : I {
    fun <!VIRTUAL_MEMBER_HIDDEN!>f<!>(): Int = 1
}

fun case_1() = O.f()
