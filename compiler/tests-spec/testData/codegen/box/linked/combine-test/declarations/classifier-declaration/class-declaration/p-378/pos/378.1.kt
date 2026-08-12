// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 378 -> sentence 378
 * declarations, declaration-visibility -> paragraph 378 -> sentence 378
 * declarations, function-declaration -> paragraph 378 -> sentence 378
 * NUMBER: 1
 * DESCRIPTION: private fun in class can be called by same-class members
 */

// TESTCASE NUMBER: 1
class C { private fun secret(): Int = 1; fun get(): Int = secret() }

// TESTCASE NUMBER: 1
fun test(): Int = C().get()

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}
