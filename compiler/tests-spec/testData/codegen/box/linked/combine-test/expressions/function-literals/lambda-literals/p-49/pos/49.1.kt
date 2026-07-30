// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 49 -> sentence 49
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 49 -> sentence 49
 * NUMBER: 1
 * DESCRIPTION: non-local return skips subsequent statements after forEach
 */

// TESTCASE NUMBER: 1
fun test(xs: List<Int>): String {
    xs.forEach { return "early" }
    return "late"
}

fun box(): String {
    if (test(listOf(1, 2)) != "early") return "NOK"
    return "OK"
}
