// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, cast-expressions -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 19 -> sentence 19
 *                expressions, when-expressions -> paragraph 19 -> sentence 19
 *                type-inference, smart-casts -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: when is branch smart cast without as
 */

// TESTCASE NUMBER: 1
fun test(x: Any): String = when (x) {
    is String -> x.uppercase()
    else -> ""
}

fun box(): String {
    if (test("hi") != "HI") return "NOK"
    if (test(1) != "") return "NOK"
    if (test("") != "") return "NOK"
    return "OK"
}
