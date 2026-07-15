// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 62 -> sentence 62
 * NUMBER: 4
 * DESCRIPTION: PARAM token on multiple constructor parameters with @param:Suppress
 */
// TESTCASE NUMBER: 1

class MultiParam62(
    @param:Suppress("UNUSED_PARAMETER") val first: Int,
    @param:Suppress("UNUSED_PARAMETER") val second: String
)

fun box(): String {
    val expected = "param-62-4"
    val holder = MultiParam62(1, expected)
    if (holder.first != 1 || holder.second != expected) return "NOK"
    return "OK"
}
