// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 381 -> sentence 381
 * declarations, declaration-visibility -> paragraph 381 -> sentence 381
 * declarations, function-declaration -> paragraph 381 -> sentence 381
 * declarations, property-declaration -> paragraph 381 -> sentence 381
 * NUMBER: 1
 * DESCRIPTION: private fun 可访问同类 private val
 */

// TESTCASE NUMBER: 1
class C { private val k = 3; private fun scale(x: Int): Int = x * k; fun test(): Int = scale(2) }

// TESTCASE NUMBER: 1
fun test(): Int = C().test()

fun box(): String {
    if (test() != 6) return "NOK"
    return "OK"
}
