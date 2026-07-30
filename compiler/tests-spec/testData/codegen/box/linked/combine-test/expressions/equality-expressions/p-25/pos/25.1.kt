// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: expressions, equality-expressions, reference-equality-expressions -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: IntArray == is referential
 */

// TESTCASE NUMBER: 1
fun test(): Boolean = intArrayOf(1) == intArrayOf(1)

fun box(): String {
    if (test()) return "NOK"
    val a = intArrayOf(1)
    if (!(a == a)) return "NOK"
    return "OK"
}
