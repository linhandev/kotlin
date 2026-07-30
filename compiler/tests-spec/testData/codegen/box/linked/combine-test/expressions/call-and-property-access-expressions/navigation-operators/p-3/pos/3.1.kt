// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 3 -> sentence 3
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 3 -> sentence 3
 *                expressions, call-expressions -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: safe call on nullable receiver calls member function and returns nullable return type, null receiver short-circuits to null
 */

// TESTCASE NUMBER: 1
data class User(val name: String) {
    fun greet() = "hi $name"
}

fun test(u: User?): String? = u?.greet()

fun box(): String {
    if (test(User("Alice")) != "hi Alice") return "NOK"
    if (test(null) != null) return "NOK"
    return "OK"
}
