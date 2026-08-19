// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: declarations, classifier-declaration, data-class-declaration -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: data class may implement an interface
 */

// TESTCASE NUMBER: 1
interface Named {
    val name: String
}

data class User(override val name: String) : Named

fun test(): String = User("Ann").name

fun box(): String {
    if (test() != "Ann") return "NOK"
    return "OK"
}
