// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, logical-conjunction-expressions -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 19 -> sentence 19
 *                expressions, when-expressions -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: when subjectless branch uses &&
 */

// TESTCASE NUMBER: 1
fun test(x: Int): String = when {
    x > 0 && x < 10 -> "ok"
    else -> "no"
}

fun box(): String {
    if (test(5) != "ok") return "NOK"
    if (test(0) != "no") return "NOK"
    if (test(10) != "no") return "NOK"
    return "OK"
}
