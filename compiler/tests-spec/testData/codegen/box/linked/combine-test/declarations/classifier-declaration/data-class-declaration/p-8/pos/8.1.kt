// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: declarations, classifier-declaration, data-class-declaration -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: generated copy can change a single property
 */

// TESTCASE NUMBER: 1
data class User(val name: String, val age: Int)

fun test(): Int = User("Ann", 1).copy(age = 2).age

fun box(): String {
    if (test() != 2) return "NOK"
    return "OK"
}
