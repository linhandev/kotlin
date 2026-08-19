// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, string-interpolation-expressions -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: property access in interpolation must be wrapped in ${}
 */

// TESTCASE NUMBER: 1
data class User(val name: String)

fun test(u: User): String = "user=${u.name}"

fun box(): String {
    if (test(User("Alice")) != "user=Alice") return "NOK"
    if (test(User("")) != "user=") return "NOK"
    return "OK"
}
