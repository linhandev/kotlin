// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: expressions, equality-expressions, reference-equality-expressions -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: string literals referential equality often true
 */

// TESTCASE NUMBER: 1
fun test(): Boolean = ("a" === "a")

fun box(): String {
    if (!test()) return "NOK"
    return "OK"
}
