// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: declarations, classifier-declaration, data-class-declaration -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: generated toString includes property names and values
 */

// TESTCASE NUMBER: 1
data class User(val name: String)

fun test(): String = User("Ann").toString()

fun box(): String {
    val s = test()
    if (!s.contains("name=Ann")) return "NOK"
    return "OK"
}
