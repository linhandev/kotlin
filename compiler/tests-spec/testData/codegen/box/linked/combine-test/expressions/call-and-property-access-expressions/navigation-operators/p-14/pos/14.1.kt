// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 14 -> sentence 14
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 14 -> sentence 14
 *                expressions, function-literals, lambda-literals -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: let with safe call allows non-null member access inside lambda, with Elvis providing default for null receiver
 */

// TESTCASE NUMBER: 1
data class User(val name: String)

fun test(u: User?): String = u?.let { it.name } ?: "guest"

fun box(): String {
    if (test(User("Alice")) != "Alice") return "NOK: non-null let returns name"
    if (test(User("Bob")) != "Bob") return "NOK: non-null let returns name 2"
    if (test(null) != "guest") return "NOK: null short-circuits to Elvis default"
    return "OK"
}
