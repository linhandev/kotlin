// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 7 -> sentence 7
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: forEach higher-order call with destructuring parameter
 */

// TESTCASE NUMBER: 1
fun test(ps: List<Pair<Int, Int>>): Int {
    var s = 0
    ps.forEach { (a, b) -> s += a + b }
    return s
}

fun box(): String {
    if (test(listOf(1 to 2, 3 to 4)) != 10) return "NOK"
    return "OK"
}
