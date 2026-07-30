// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, object-declaration -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 10 -> sentence 10
 *                declarations, property-declaration -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: abstract interface properties must be implemented by object
 */

// TESTCASE NUMBER: 1
interface Named {
    val name: String
}

object User : Named {
    override val name: String = "Ann"
}

fun test(): String = User.name

fun box(): String {
    if (test() != "Ann") return "NOK"
    return "OK"
}
