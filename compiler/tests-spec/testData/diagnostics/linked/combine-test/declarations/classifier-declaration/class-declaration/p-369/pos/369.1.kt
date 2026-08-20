// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 369 -> sentence 369
 * declarations, declaration-visibility -> paragraph 369 -> sentence 369
 * declarations, property-declaration -> paragraph 369 -> sentence 369
 * NUMBER: 1
 * DESCRIPTION: private getter can be read inside class type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class C { private val v: Int get() = 2; fun get(): Int = v }

// TESTCASE NUMBER: 1
fun test(): Int = C().get()

fun case1() {
    checkSubtype<Int>(test())
}
