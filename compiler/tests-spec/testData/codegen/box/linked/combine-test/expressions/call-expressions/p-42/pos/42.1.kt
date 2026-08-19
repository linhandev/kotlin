// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 42 -> sentence 42
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 42 -> sentence 42
 *                type-inference, introduction-1 -> paragraph 42 -> sentence 42
 *                expressions, function-literals, lambda-literals -> paragraph 42 -> sentence 42
 * NUMBER: 1
 * DESCRIPTION: higher-order stdlib call infers type arguments from lambda argument
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val xs = listOf("a", "bc")
    val result = xs.map { it.length }
    if (result != listOf(1, 2)) return "NOK"
    val filtered = xs.filter { it.length > 1 }
    if (filtered != listOf("bc")) return "NOK"
    return "OK"
}
