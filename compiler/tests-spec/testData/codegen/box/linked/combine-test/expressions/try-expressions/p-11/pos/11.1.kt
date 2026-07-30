// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: type-inference, introduction-1 -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: multiple catch branches participate in overall type inference to Any
 */

// TESTCASE NUMBER: 1
fun test(kind: Int): Any = try {
    when (kind) {
        1 -> throw IllegalArgumentException()
        2 -> throw Exception()
        else -> 1
    }
} catch (e: IllegalArgumentException) {
    "bad"
} catch (e: Exception) {
    false
}

fun box(): String {
    if (test(0) != 1) return "NOK"
    if (test(1) != "bad") return "NOK"
    if (test(2) != false) return "NOK"
    return "OK"
}
