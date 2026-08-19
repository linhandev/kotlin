// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: type-inference, introduction-1 -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: finally block does not change try expression result type
 */

// TESTCASE NUMBER: 1
fun test(): Int = try {
    1
} catch (e: Exception) {
    2
} finally {
    println("done")
}

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}
