// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 385 -> sentence 385
 * declarations, declaration-visibility -> paragraph 385 -> sentence 385
 * declarations, function-declaration -> paragraph 385 -> sentence 385
 * declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 385 -> sentence 385
 * NUMBER: 1
 * DESCRIPTION: inner class can call outer class private fun type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Outer { private fun secret(): Int = 7; inner class Inner { fun get(): Int = secret() } }

// TESTCASE NUMBER: 1
fun test(): Int = Outer().Inner().get()

fun case1() {
    checkSubtype<Int>(test())
}
