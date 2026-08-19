// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 86 -> sentence 86
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 86 -> sentence 86
 * NUMBER: 1
 * DESCRIPTION: primary and secondary constructors combine with default parameters
 */

// TESTCASE NUMBER: 1
class User(val name: String, val age: Int = 0) {
    constructor(name: String) : this(name, 0)
}

fun test(): Int = User("Ann").age


fun box(): String {
    if (test() != 0) return "NOK"
    return "OK"
}
