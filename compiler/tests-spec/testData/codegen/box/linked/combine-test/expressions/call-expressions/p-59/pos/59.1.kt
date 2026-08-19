// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 59 -> sentence 59
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 59 -> sentence 59
 *                type-inference, introduction-1 -> paragraph 59 -> sentence 59
 * NUMBER: 1
 * DESCRIPTION: SAM-like function type with generic trailing lambda infers type argument correctly
 */

// TESTCASE NUMBER: 1
fun <T> apply(x: T, block: (T) -> T): T = block(x)

fun box(): String {
    if (apply(1) { it + 1 } != 2) return "NOK"
    if (apply("a") { it + "b" } != "ab") return "NOK"
    return "OK"
}
