// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 117 -> sentence 117
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 117 -> sentence 117
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 117 -> sentence 117
 * NUMBER: 1
 * DESCRIPTION: property initializer may use primary constructor parameter in class declaration
 */

// TESTCASE NUMBER: 1
class User(val name: String) {
    val upper = name.uppercase()
}

fun viaLower(): String = User("a").upper

fun viaMixed(): String = User("AbC").upper

fun viaEmpty(): String = User("").upper

fun box(): String {
    if (viaLower() != "A") return "NOK: lower"
    if (viaMixed() != "ABC") return "NOK: mixed"
    if (viaEmpty() != "") return "NOK: empty"
    return "OK"
}
