// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, object-literals -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: object literal can inherit class and implement interface
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
open class Base
interface Extra { fun extra(): Int }

fun case_1(): Int = object : Base(), Extra {
    override fun extra(): Int = 7
}.extra()

fun case_1_check() {
    checkSubtype<Int>(case_1())
}
