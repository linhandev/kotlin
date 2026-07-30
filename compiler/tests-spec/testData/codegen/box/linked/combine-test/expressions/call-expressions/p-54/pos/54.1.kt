// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 54 -> sentence 54
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 54 -> sentence 54
 *                declarations, declaration-site-variance-and-use-site-variance -> paragraph 54 -> sentence 54
 * NUMBER: 1
 * DESCRIPTION: star-projected type as receiver in call to non-generic member
 */

// TESTCASE NUMBER: 1
fun test(xs: List<*>): Int = xs.size

fun box(): String {
    if (test(listOf(1, 2, 3)) != 3) return "NOK"
    if (test(listOf("a", "b")) != 2) return "NOK"
    if (test(emptyList<Any>()) != 0) return "NOK"
    return "OK"
}
