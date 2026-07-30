// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 45 -> sentence 45
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 45 -> sentence 45
 *                type-inference, smart-casts -> paragraph 45 -> sentence 45
 *                type-inference, introduction-1 -> paragraph 45 -> sentence 45
 * NUMBER: 1
 * DESCRIPTION: when expression with is branches returning different types infers common supertype Any
 */

// TESTCASE NUMBER: 1
fun test(x: Any): Any = when (x) {
    is String -> x.length
    is Int -> x.toString()
    else -> false
}

fun box(): String {
    if (test("hi") != 2) return "NOK"
    if (test(1) != "1") return "NOK"
    if (test(1.5) != false) return "NOK"
    return "OK"
}
