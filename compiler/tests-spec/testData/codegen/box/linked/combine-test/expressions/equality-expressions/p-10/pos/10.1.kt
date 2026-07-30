// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: expressions, equality-expressions, reference-equality-expressions -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: structural == true while referential !== for distinct instances
 */

// TESTCASE NUMBER: 1
data class P(val x: Int)

fun test(): Boolean = P(1) == P(1) && P(1) !== P(1)

fun box(): String {
    if (!test()) return "NOK"
    val a = P(1)
    if (!(a == a && a === a)) return "NOK"
    return "OK"
}
