// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 360 -> sentence 360
 * declarations, declaration-visibility -> paragraph 360 -> sentence 360
 * declarations, property-declaration -> paragraph 360 -> sentence 360
 * NUMBER: 1
 * DESCRIPTION: member function can access private properties of same class
 */

// TESTCASE NUMBER: 1
class C { private val k = 10; private fun scale(x: Int): Int = x * k; fun test(): Int = scale(2) }

// TESTCASE NUMBER: 1
fun test(): Int = C().test()

fun box(): String {
    if (test() != 20) return "NOK"
    return "OK"
}
