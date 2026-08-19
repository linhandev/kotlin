// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: type-system, built-in-integer-types -> paragraph 24 -> sentence 24
 *                syntax-and-grammar, lexical-grammar, literals -> paragraph 24 -> sentence 24
 *                expressions, call-expressions -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: arrayOf with separator Long literals preserves element values at runtime
 */

// TESTCASE NUMBER: 1
fun test(): Long = arrayOf(1_000L, 2_000L)[0]

fun box(): String {
    if (test() != 1000L) return "NOK: arrayOf separator Long literal first element"
    val arr = arrayOf(1_000L, 2_000L)
    if (arr[1] != 2000L) return "NOK: arrayOf separator Long literal second element"
    if (arr.size != 2) return "NOK: arrayOf size with separator Long literals"
    if (arr[0] + arr[1] != 3_000L) return "NOK: separator Long literals in array element arithmetic"
    return "OK"
}
