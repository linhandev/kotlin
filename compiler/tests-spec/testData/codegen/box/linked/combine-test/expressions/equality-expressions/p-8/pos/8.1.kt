// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: expressions, equality-expressions, reference-equality-expressions -> paragraph 8 -> sentence 8
 *                type-system, introduction-1 -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: null referential equals null is true
 */

// TESTCASE NUMBER: 1
fun test(): Boolean = null === null

fun box(): String {
    if (!test()) return "NOK"
    return "OK"
}
