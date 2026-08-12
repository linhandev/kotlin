// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 351 -> sentence 351
 * declarations, declaration-visibility -> paragraph 351 -> sentence 351
 * declarations, property-declaration -> paragraph 351 -> sentence 351
 * NUMBER: 1
 * DESCRIPTION: private val in class body is readable only inside class type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class C { private val secret = 1; fun get(): Int = secret }

// TESTCASE NUMBER: 1
fun test(): Int = C().get()

fun case1() {
    checkSubtype<Int>(test())
}
