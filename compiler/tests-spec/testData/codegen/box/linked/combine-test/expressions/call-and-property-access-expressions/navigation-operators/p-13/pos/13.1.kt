// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 13 -> sentence 13
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 13 -> sentence 13
 *                type-inference, smart-casts -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: smart cast after null check allows direct member access on originally nullable receiver, no safe call needed
 */

// TESTCASE NUMBER: 1
data class User(val name: String)

fun test(u: User?): String = if (u != null) u.name else "guest"

fun box(): String {
    if (test(User("Alice")) != "Alice") return "NOK: non-null returns name"
    if (test(User("Bob")) != "Bob") return "NOK: non-null returns name 2"
    if (test(null) != "guest") return "NOK: null returns default"
    return "OK"
}
