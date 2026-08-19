// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: type-inference, introduction-1 -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: missing matching catch does not affect static type inference
 */

// TESTCASE NUMBER: 1
fun test(): Int = try {
    1
} catch (e: IllegalArgumentException) {
    2
}

fun testCaught(): Int = try {
    throw IllegalArgumentException()
} catch (e: IllegalArgumentException) {
    2
}

fun box(): String {
    if (test() != 1) return "NOK"
    if (testCaught() != 2) return "NOK"
    return "OK"
}
