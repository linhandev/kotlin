// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: syntax-and-grammar, lexical-grammar, literals -> paragraph 21 -> sentence 21
 *                declarations, property-declaration -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: const val initialized with separator integer literal preserves value in expressions
 */

// TESTCASE NUMBER: 1
const val MAX = 1_000

fun test(): Int = MAX

fun box(): String {
    if (test() != 1000) return "NOK: const val with separator literal return value"
    if (MAX != 1_000) return "NOK: const val equals separator literal"
    if (MAX + 2_000 != 3_000) return "NOK: const val in arithmetic with separator literal"
    if (MAX != 999 + 1) return "NOK: const val in expression context with separator literal"
    return "OK"
}
