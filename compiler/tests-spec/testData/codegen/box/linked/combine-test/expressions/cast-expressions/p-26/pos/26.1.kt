// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, cast-expressions -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 26 -> sentence 26
 *                type-inference, smart-casts -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: elvis smart cast to non-null then as String
 */

// TESTCASE NUMBER: 1
fun test(x: Any?): Int {
    val s = x ?: return 0
    return (s as String).length
}

fun box(): String {
    if (test(null) != 0) return "NOK"
    if (test("hi") != 2) return "NOK"
    try {
        test(1)
        return "NOK"
    } catch (_: ClassCastException) {
    }
    return "OK"
}
