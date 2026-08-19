// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: Array contentEquals true while == referential false
 */

// TESTCASE NUMBER: 1
fun testContent(): Boolean = arrayOf(1, 2).contentEquals(arrayOf(1, 2))
fun testEq(): Boolean = arrayOf(1, 2) == arrayOf(1, 2)

fun box(): String {
    if (!testContent()) return "NOK"
    if (testEq()) return "NOK"
    return "OK"
}
