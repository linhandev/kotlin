// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 48 -> sentence 48
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 48 -> sentence 48
 *                type-inference, introduction-1 -> paragraph 48 -> sentence 48
 * NUMBER: 1
 * DESCRIPTION: chained generic calls infer type arguments step by step
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val xs = listOf("hello", "world", "")
    val result: List<String> = xs.map { it.uppercase() }.filter { it.isNotEmpty() }
    if (result != listOf("HELLO", "WORLD")) return "NOK"

    val nums = listOf(1, 2, 3).map { it * 2 }.filter { it > 0 }
    if (nums != listOf(2, 4, 6)) return "NOK"

    return "OK"
}
