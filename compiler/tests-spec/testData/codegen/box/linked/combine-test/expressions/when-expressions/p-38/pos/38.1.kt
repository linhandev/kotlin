// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 38 -> sentence 38
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 38 -> sentence 38
 *                type-inference, smart-casts -> paragraph 38 -> sentence 38
 * NUMBER: 1
 * DESCRIPTION: when expression with !is branch excluding type and else branch smart cast
 */

// TESTCASE NUMBER: 1
fun test(x: Any): String = when (x) {
    !is String -> "not string"
    else -> x.uppercase()
}

fun box(): String {
    if (test(123) != "not string") return "NOK"
    if (test("hi") != "HI") return "NOK"
    if (test("") != "") return "NOK"
    return "OK"
}
