// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 48 -> sentence 48
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 48 -> sentence 48
 *                expressions, when-expressions -> paragraph 48 -> sentence 48
 * NUMBER: 1
 * DESCRIPTION: when used as statement with is branch only for Any subject
 */

// TESTCASE NUMBER: 1
fun test(x: Any): String {
    var result = "none"
    when (x) {
        is String -> result = "len=${x.length}"
    }
    return result
}

fun box(): String {
    if (test("hi") != "len=2") return "NOK"
    if (test(123) != "none") return "NOK"
    if (test("") != "len=0") return "NOK"
    return "OK"
}
