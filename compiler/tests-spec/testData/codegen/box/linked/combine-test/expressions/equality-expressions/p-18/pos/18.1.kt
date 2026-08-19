// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: expressions, equality-expressions, reference-equality-expressions -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: default equals is referential for plain class
 */

// TESTCASE NUMBER: 1
class C(val v: Int)

fun test(): Boolean = C(1) == C(1)

fun box(): String {
    if (test()) return "NOK"
    val a = C(1)
    if (!(a == a)) return "NOK"
    return "OK"
}
