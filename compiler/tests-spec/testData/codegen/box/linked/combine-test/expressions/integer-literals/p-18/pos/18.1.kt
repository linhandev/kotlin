// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: type-system, built-in-integer-types -> paragraph 18 -> sentence 18
 *                expressions, equality-expressions -> paragraph 18 -> sentence 18
 *                syntax-and-grammar, lexical-grammar, literals -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: Int separator literal converted to Long equals Long separator literal at runtime
 */

// TESTCASE NUMBER: 1
fun test(): Boolean = 1_000.toLong() == 1_000L

fun box(): String {
    if (!test()) return "NOK: separator Int literal toLong equals separator Long literal"
    if (!1.toLong().equals(1L)) return "NOK: plain Int toLong equals plain Long literal"
    if (2_000.toLong() != 2_000L) return "NOK: another separator literal pair after conversion"
    if (10_00.toLong() + 1L != 10_01L) return "NOK: converted Int literal in Long arithmetic context"
    return "OK"
}
