// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 91 -> sentence 91
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 91 -> sentence 91
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 91 -> sentence 91
 *                declarations, property-declaration -> paragraph 91 -> sentence 91
 * NUMBER: 1
 * DESCRIPTION: secondary constructor this() fills primary params that drive derived property initializers in class declaration
 */

// TESTCASE NUMBER: 1
class User(name: String, age: Int) {
    val display: String = "$name#$age"
    constructor(name: String) : this(name, 0)
}

fun viaSecondary(): String = User("Ann").display

fun viaPrimary(): String = User("Bob", 25).display

fun box(): String {
    if (viaSecondary() != "Ann#0") return "NOK: secondary"
    if (viaPrimary() != "Bob#25") return "NOK: primary"
    return "OK"
}
