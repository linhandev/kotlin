// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 355 -> sentence 355
 * declarations, declaration-visibility -> paragraph 355 -> sentence 355
 * declarations, property-declaration -> paragraph 355 -> sentence 355
 * declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 355 -> sentence 355
 * NUMBER: 1
 * DESCRIPTION: primary constructor private val creates private property
 */

// TESTCASE NUMBER: 1
class User(private val id: Int) { fun get(): Int = id }

// TESTCASE NUMBER: 1
fun test(): Int = User(1).get()

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}
