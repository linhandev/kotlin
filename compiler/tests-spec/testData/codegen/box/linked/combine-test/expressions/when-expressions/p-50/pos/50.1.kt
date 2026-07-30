// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 50 -> sentence 50
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 50 -> sentence 50
 *                runtime-type-information, runtime-available-types -> paragraph 50 -> sentence 50
 * NUMBER: 1
 * DESCRIPTION: when expression is check against List star projection is runtime-available
 */

// TESTCASE NUMBER: 1
fun test(x: Any): Int = when (x) {
    is List<*> -> x.size
    else -> -1
}

fun box(): String {
    if (test(listOf("a", "b")) != 2) return "NOK"
    if (test(listOf(1)) != 1) return "NOK"
    if (test(123) != -1) return "NOK"
    if (test(emptyList<Any>()) != 0) return "NOK"
    return "OK"
}
