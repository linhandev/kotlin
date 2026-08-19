// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 11 -> sentence 11
 *                expressions, not-null-assertion-expressions -> paragraph 11 -> sentence 11
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: non-null assertion on nullable receiver allows direct member access, throws NPE if null
 */

// TESTCASE NUMBER: 1
data class User(val name: String)
fun test(u: User?): String = u!!.name

fun box(): String {
    if (test(User("Alice")) != "Alice") return "NOK"
    if (test(User("Bob")) != "Bob") return "NOK"
    return "OK"
}
