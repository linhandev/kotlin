// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 30 -> sentence 30
 * PRIMARY LINKS: expressions, equality-expressions, reference-equality-expressions -> paragraph 30 -> sentence 30
 * NUMBER: 1
 * DESCRIPTION: object instance === itself
 */

// TESTCASE NUMBER: 1
object O

fun test(): Boolean = O === O

fun box(): String {
    if (!test()) return "NOK"
    return "OK"
}
