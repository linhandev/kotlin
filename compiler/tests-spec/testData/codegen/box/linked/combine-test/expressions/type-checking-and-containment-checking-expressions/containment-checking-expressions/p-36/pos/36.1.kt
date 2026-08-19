// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 36 -> sentence 36
 * PRIMARY LINKS: expressions, multiplicative-expressions -> paragraph 36 -> sentence 36
 *                operator-overloading, overview -> paragraph 36 -> sentence 36
 * NUMBER: 1
 * DESCRIPTION: multiplicative expression result used with in and not-in on List<Int> at runtime
 */

// TESTCASE NUMBER: 1
fun test(): Boolean = (2 * 3) in listOf(6, 7) && (2 * 4) !in listOf(6, 7)

fun box(): String {
    if (!test()) return "NOK: multiplicative lhs containment"
    if ((2 * 5) in listOf(6, 7)) return "NOK: absent product must be false"
    if (!((3 * 3) !in listOf(6, 7))) return "NOK: not-in for absent product"
    return "OK"
}
