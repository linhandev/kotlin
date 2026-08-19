// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, object-literals -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: object literal can override default interface implementation
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface I {
    fun f(): Int = 1
}

fun case_1(): Int = object : I {
    override fun f(): Int = 2
}.f()

fun case_1_check() {
    checkSubtype<Int>(case_1())
}
