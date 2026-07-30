// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, cast-expressions -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 13 -> sentence 13
 *                type-inference, smart-casts -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: !is then else branch smart cast without as
 */

// TESTCASE NUMBER: 1
fun test(x: Any): String = if (x !is String) "n" else x.uppercase()

fun box(): String {
    if (test("hi") != "HI") return "NOK"
    if (test(1) != "n") return "NOK"
    if (test("") != "") return "NOK"
    return "OK"
}
