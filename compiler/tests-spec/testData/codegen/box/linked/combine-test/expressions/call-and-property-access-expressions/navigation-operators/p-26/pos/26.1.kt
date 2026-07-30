// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 26 -> sentence 26
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 26 -> sentence 26
 *                declarations, property-declaration, late-initialized-properties -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: safe call on non-null lateinit property returns nullable result, lateinit type is String not String?
 */

// TESTCASE NUMBER: 1
class C {
    lateinit var s: String
}

fun test(c: C): Int? = c.s?.length

fun box(): String {
    val c = C()
    c.s = "kotlin"
    if (test(c) != 6) return "NOK: returns length"
    return "OK"
}
