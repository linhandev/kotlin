// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 21 -> sentence 21
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 21 -> sentence 21
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: safe call after safe cast on nullable Any? returns uppercase string or null
 */

// TESTCASE NUMBER: 1
fun test(x: Any?): String? = (x as? String)?.uppercase()

fun box(): String {
    if (test("hello") != "HELLO") return "NOK: string returns uppercase"
    if (test(1) != null) return "NOK: non-string returns null"
    if (test(null) != null) return "NOK: null returns null"
    return "OK"
}
