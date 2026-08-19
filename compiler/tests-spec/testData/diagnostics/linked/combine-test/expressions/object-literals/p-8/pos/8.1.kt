// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, object-literals -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: object literal can override open member
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
open class Base {
    open fun f(): Int = 1
}

fun case_1(): Int = object : Base() {
    override fun f(): Int = 2
}.f()

fun case_1_check() {
    checkSubtype<Int>(case_1())
}
