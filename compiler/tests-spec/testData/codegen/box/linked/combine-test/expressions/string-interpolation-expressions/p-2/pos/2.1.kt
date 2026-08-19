// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, string-interpolation-expressions -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: simple interpolation only includes object before dot, remainder is literal text
 */

// TESTCASE NUMBER: 1
data class User(val name: String)

fun test(u: User): String = "user=$u.name"

fun box(): String {
    val result = test(User("Alice"))
    if (!result.startsWith("user=User")) return "NOK"
    if (!result.endsWith(".name")) return "NOK"
    if (result == "user=Alice") return "NOK"
    return "OK"
}
