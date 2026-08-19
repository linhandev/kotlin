// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 25 -> sentence 25
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: safe call on nullable receiver whose member returns nullable type yields nullable result, null receiver short-circuits
 */

// TESTCASE NUMBER: 1
class C {
    fun maybe(): String? = null
}

fun test(c: C?): String? = c?.maybe()

fun box(): String {
    if (test(C()) != null) return "NOK: maybe() returns null"
    if (test(null) != null) return "NOK: null receiver short-circuits"
    return "OK"
}
