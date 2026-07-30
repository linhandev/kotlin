// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: type-inference, introduction-1 -> paragraph 15 -> sentence 15
 *                built-in-types-and-their-semantics, kotlin.nothing -> paragraph 15 -> sentence 15
 *                type-system, introduction-1 -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: nullable try branch with Nothing catch remains String?
 */

// TESTCASE NUMBER: 1
fun test(flag: Boolean): String? = try {
    if (flag) "ok" else null
} catch (e: Exception) {
    throw e
}

fun box(): String {
    if (test(true) != "ok") return "NOK"
    if (test(false) != null) return "NOK"
    return "OK"
}
