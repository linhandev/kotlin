// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: type-inference, introduction-1 -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: try and catch returning unrelated types infer common supertype Any
 */

// TESTCASE NUMBER: 1
fun test(flag: Boolean): Any = try {
    if (flag) 1 else throw Exception()
} catch (e: Exception) {
    "error"
}

fun box(): String {
    if (test(true) != 1) return "NOK"
    if (test(false) != "error") return "NOK"
    return "OK"
}
