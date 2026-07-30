// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 30 -> sentence 30
 * PRIMARY LINKS: expressions, elvis-operator-expressions -> paragraph 30 -> sentence 30
 * NUMBER: 1
 * DESCRIPTION: Elvis with non-nullable left inside try does not change try expression result
 */

// TESTCASE NUMBER: 1
@Suppress("USELESS_ELVIS")
fun test(): Int = try {
    1 ?: 2
} catch (e: Exception) {
    0
}

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}
