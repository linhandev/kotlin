// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, object-literals -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: object literal can inherit open class
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
open class Base(open val v: Int)

fun case_1(): Int = object : Base(1) {
    override val v: Int = 2
}.v

fun case_1_check() {
    checkSubtype<Int>(case_1())
}
