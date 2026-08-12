// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 393 -> sentence 393
 * declarations, declaration-visibility -> paragraph 393 -> sentence 393
 * declarations, function-declaration -> paragraph 393 -> sentence 393
 * NUMBER: 1
 * DESCRIPTION: private fun supports recursive calls type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class C { private fun fact(n: Int): Int = if (n <= 1) 1 else n * fact(n - 1); fun test(): Int = fact(4) }

// TESTCASE NUMBER: 1
fun test(): Int = C().test()

fun case1() {
    checkSubtype<Int>(test())
}
