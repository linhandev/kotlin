// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, object-literals -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: object literal supports class delegation to implement interface
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface I {
    fun f(): Int
}

class Impl : I {
    override fun f(): Int = 1
}

fun case_1(): Int = (object : I by Impl() {}).f()

fun case_1_check() {
    checkSubtype<Int>(case_1())
}
