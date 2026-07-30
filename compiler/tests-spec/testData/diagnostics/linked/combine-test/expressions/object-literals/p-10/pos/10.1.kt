// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, object-literals -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: object literal inherits default interface implementation
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface I {
    fun f(): Int = 1
}

fun case_1(): Int = object : I {}.f()

fun case_1_check() {
    checkSubtype<Int>(case_1())
}
