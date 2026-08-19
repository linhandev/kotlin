// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: destructuring parameter remains single-arg inside receiver lambda
 */

// TESTCASE NUMBER: 1
fun test(ps: MutableList<Pair<Int, Int>>): Int {
    var s = 0
    ps.apply { forEach { (a, b) -> s += a + b } }
    return s
}

fun box(): String {
    if (test(mutableListOf(1 to 2, 3 to 4)) != 10) return "NOK"
    return "OK"
}
