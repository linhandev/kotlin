// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 358 -> sentence 358
 * declarations, declaration-visibility -> paragraph 358 -> sentence 358
 * declarations, property-declaration -> paragraph 358 -> sentence 358
 * declarations, classifier-declaration, classifier-initialization -> paragraph 358 -> sentence 358
 * NUMBER: 1
 * DESCRIPTION: init block can read and write declared private var
 */

// TESTCASE NUMBER: 1
class C { private var n: Int; init { n = 1 }; fun get(): Int = n }

// TESTCASE NUMBER: 1
fun test(): Int = C().get()

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}
