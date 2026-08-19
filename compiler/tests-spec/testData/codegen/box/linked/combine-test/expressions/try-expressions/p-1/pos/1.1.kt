// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: type-inference, introduction-1 -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: try and catch returning the same type infers that type for the try expression
 */

// TESTCASE NUMBER: 1
fun test(): Int = try {
    1
} catch (e: Exception) {
    2
}

fun throwAndCatch(): Int = try {
    throw IllegalStateException()
} catch (e: Exception) {
    2
}

fun box(): String {
    if (test() != 1) return "NOK"
    if (throwAndCatch() != 2) return "NOK"
    return "OK"
}
