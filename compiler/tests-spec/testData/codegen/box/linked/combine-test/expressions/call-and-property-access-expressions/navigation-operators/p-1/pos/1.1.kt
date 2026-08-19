// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 1 -> sentence 1
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 1 -> sentence 1
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: safe call on nullable receiver accesses non-null member and returns nullable type, null receiver short-circuits to null
 */

// TESTCASE NUMBER: 1
data class User(val name: String)
fun test(u: User?): String? = u?.name

fun box(): String {
    if (test(User("Alice")) != "Alice") return "NOK"
    if (test(null) != null) return "NOK"
    return "OK"
}
