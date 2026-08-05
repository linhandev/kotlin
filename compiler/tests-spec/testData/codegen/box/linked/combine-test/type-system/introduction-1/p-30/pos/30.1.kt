// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 30 -> sentence 30
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 30 -> sentence 30
 *                expressions, equality-expressions -> paragraph 30 -> sentence 30
 *                expressions, cast-expressions -> paragraph 30 -> sentence 30
 * NUMBER: 1
 * DESCRIPTION: List equality compares elements after erasure, independent of declared type arguments
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val a: List<Int> = listOf(1, 2)
    val b: List<Any> = listOf(1, 2)
    if (a != b) return "NOK"
    if ((a as List<*>) != (b as List<*>)) return "NOK"
    if (a == listOf(1, 3)) return "NOK"
    return "OK"
}
