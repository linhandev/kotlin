// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, cast-expressions -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 24 -> sentence 24
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: as? then !! yields non-null or NPE
 */

// TESTCASE NUMBER: 1
fun test(x: Any): String = (x as? String)!!

fun box(): String {
    if (test("hi") != "hi") return "NOK"
    try {
        test(1)
        return "NOK"
    } catch (_: NullPointerException) {
    }
    return "OK"
}
