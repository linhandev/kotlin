// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 30 -> sentence 30
 * PRIMARY LINKS: declarations, classifier-declaration, data-class-declaration -> paragraph 30 -> sentence 30
 * NUMBER: 1
 * DESCRIPTION: copy with named argument updates the selected property
 */

// TESTCASE NUMBER: 1
data class User(val name: String, val age: Int)

fun test(): String = User("A", 1).copy(name = "B").name

fun box(): String {
    if (test() != "B") return "NOK"
    return "OK"
}
