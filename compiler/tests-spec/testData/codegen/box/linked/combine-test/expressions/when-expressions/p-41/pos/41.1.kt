// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 41 -> sentence 41
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 41 -> sentence 41
 *                type-system, introduction-1 -> paragraph 41 -> sentence 41
 *                expressions, when-expressions -> paragraph 41 -> sentence 41
 * NUMBER: 1
 * DESCRIPTION: when expression with nullable subject is branch and else branch covering null
 */

// TESTCASE NUMBER: 1
fun test(x: Any?): Int = when (x) {
    is String -> x.length
    else -> -1
}

fun box(): String {
    if (test("hi") != 2) return "NOK"
    if (test(null) != -1) return "NOK"
    if (test(123) != -1) return "NOK"
    return "OK"
}
