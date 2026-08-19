// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 7 -> sentence 7
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: non-null receiver does not require safe call, direct member access returns non-null type
 */

// TESTCASE NUMBER: 1
data class User(val name: String)
fun test(u: User): String = u.name

fun box(): String {
    if (test(User("Alice")) != "Alice") return "NOK"
    if (test(User("Bob")) != "Bob") return "NOK"
    return "OK"
}
