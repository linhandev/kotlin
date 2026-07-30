// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 63 -> sentence 63
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 63 -> sentence 63
 * NUMBER: 1
 * DESCRIPTION: plain primary constructor parameter is not a property but usable in body
 */

// TESTCASE NUMBER: 1
class User(name: String) { val label = name }

fun test(): String = User("Ann").label

fun box(): String {
    if (test() != "Ann") return "NOK"
    return "OK"
}
