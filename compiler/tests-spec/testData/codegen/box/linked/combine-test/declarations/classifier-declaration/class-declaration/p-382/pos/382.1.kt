// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 382 -> sentence 382
 * declarations, declaration-visibility -> paragraph 382 -> sentence 382
 * declarations, function-declaration -> paragraph 382 -> sentence 382
 * declarations, classifier-declaration, classifier-initialization -> paragraph 382 -> sentence 382
 * NUMBER: 1
 * DESCRIPTION: init block can call private fun
 */

// TESTCASE NUMBER: 1
class C(private val seed: Int) { private fun double(): Int = seed * 2; val v: Int; init { v = double() }; fun get(): Int = v }

// TESTCASE NUMBER: 1
fun test(): Int = C(3).get()

fun box(): String {
    if (test() != 6) return "NOK"
    return "OK"
}
