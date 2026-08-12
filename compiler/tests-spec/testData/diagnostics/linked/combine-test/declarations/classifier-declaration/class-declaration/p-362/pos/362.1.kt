// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 362 -> sentence 362
 * declarations, declaration-visibility -> paragraph 362 -> sentence 362
 * declarations, property-declaration -> paragraph 362 -> sentence 362
 * declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 362 -> sentence 362
 * NUMBER: 1
 * DESCRIPTION: inner class can access outer class private val type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Outer(private val secret: Int) { inner class Inner { fun get(): Int = secret } }

// TESTCASE NUMBER: 1
fun test(): Int = Outer(5).Inner().get()

fun case1() {
    checkSubtype<Int>(test())
}
