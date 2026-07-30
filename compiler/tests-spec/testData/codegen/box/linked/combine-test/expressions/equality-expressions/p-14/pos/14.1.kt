// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: string template equals concatenation of same content
 */

// TESTCASE NUMBER: 1
fun test(name: String): Boolean = "Hello, $name" == "Hello, " + name

fun box(): String {
    if (!test("Kotlin")) return "NOK"
    if (test("x") != ("Hello, x" == "Hello, " + "x")) return "NOK"
    if ("ab" != "a" + "b") return "NOK"
    if ("ab" == "ac") return "NOK"
    return "OK"
}
