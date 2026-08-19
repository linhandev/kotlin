// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: type-system, built-in-integer-types -> paragraph 12 -> sentence 12
 *                syntax-and-grammar, lexical-grammar, literals -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: explicit Long variable accepts separator Long literal and preserves value in expressions
 */

// TESTCASE NUMBER: 1
fun test(): Long {
    val x: Long = 1_000L
    return x
}

fun box(): String {
    if (test() != 1000L) return "NOK: Long variable initialized with separator literal"
    val x: Long = 1_000L
    val y: Long = 2_000L
    if (x + y != 3_000L) return "NOK: Long variables with separator literals in arithmetic"
    val z: Long = x
    if (z != 1_000L) return "NOK: Long variable copy preserves separator literal value"
    return "OK"
}
