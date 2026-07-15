// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 87 -> sentence 87
 * NUMBER: 3
 * DESCRIPTION: FINALLY token in finally block with side effect
 */
// TESTCASE NUMBER: 1
fun finallySide87(): Pair<String, Boolean> {
    val expected = "finally-87-3"
    var cleaned = false
    val result = try {
        expected
    } finally {
        cleaned = true
    }
    return result to cleaned
}

fun box(): String {
    val expected = "finally-87-3"
    val (result, cleaned) = finallySide87()
    if (result != expected || !cleaned) return "NOK"
    return "OK"
}
