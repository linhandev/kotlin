// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: expressions, equality-expressions, reference-equality-expressions -> paragraph 9 -> sentence 9
 *                type-system, introduction-1 -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: null referential equals non-null is false
 */

// TESTCASE NUMBER: 1
fun test(): Boolean = null === "a"

fun box(): String {
    if (test()) return "NOK"
    return "OK"
}
