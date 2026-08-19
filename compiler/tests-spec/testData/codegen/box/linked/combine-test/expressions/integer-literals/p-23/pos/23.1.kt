// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: type-system, built-in-integer-types -> paragraph 23 -> sentence 23
 *                syntax-and-grammar, lexical-grammar, literals -> paragraph 23 -> sentence 23
 *                declarations, function-declaration -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: separator Long literal as default parameter value preserves value when argument omitted
 */

// TESTCASE NUMBER: 1
fun def(v: Long = 1_000L): Long = v

fun test(): Long = def()

fun box(): String {
    if (test() != 1000L) return "NOK: default parameter with separator Long literal"
    if (def() != 1_000L) return "NOK: calling without argument uses separator Long default value"
    if (def(2_000L) != 2000L) return "NOK: explicit separator Long argument overrides default"
    if (def(10_00L) != 1000L) return "NOK: separator at different position in explicit argument"
    return "OK"
}
