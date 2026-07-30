// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 29 -> sentence 29
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 29 -> sentence 29
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 29 -> sentence 29
 *                expressions, when-expressions -> paragraph 29 -> sentence 29
 *                type-inference, smart-casts -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: when expression with null check branch smart-casts to non-null for direct member access in else branch
 */

// TESTCASE NUMBER: 1
data class User(val name: String)

fun test(u: User?): String = when (u) {
    null -> "guest"
    else -> u.name
}

fun box(): String {
    if (test(User("Alice")) != "Alice") return "NOK: non-null returns name"
    if (test(null) != "guest") return "NOK: null returns guest"
    return "OK"
}
