// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 67 -> sentence 67
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 67 -> sentence 67
 * NUMBER: 1
 * DESCRIPTION: named arguments map to primary constructor parameters
 */

// TESTCASE NUMBER: 1
class User(val name: String, val age: Int)

fun test(): User = User(name = "Ann", age = 1)

fun box(): String {
    if (test().name != "Ann" || test().age != 1) return "NOK"
    return "OK"
}
