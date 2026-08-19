// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: type-system, built-in-integer-types -> paragraph 22 -> sentence 22
 *                syntax-and-grammar, lexical-grammar, literals -> paragraph 22 -> sentence 22
 *                expressions, call-expressions -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: separator Long literal passed to Long parameter in function call preserves value
 */

// TESTCASE NUMBER: 1
fun id(x: Long): Long = x

fun test(): Long = id(1_000L)

fun box(): String {
    if (test() != 1000L) return "NOK: separator Long literal passed to Long parameter"
    if (id(2_000L) != 2000L) return "NOK: another separator Long literal in function call"
    if (id(1_000L) + id(500L) != 1500L) return "NOK: separator Long literals in chained function calls"
    if (id(10_00L) != 1000L) return "NOK: separator at different position in function argument"
    return "OK"
}
