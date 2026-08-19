// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, indexing-expressions -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: out-of-bounds index throws ArrayIndexOutOfBoundsException
 */

// TESTCASE NUMBER: 1
fun test(): Int = arrayOf(1)[1]

fun box(): String {
    try {
        test()
        return "NOK"
    } catch (_: ArrayIndexOutOfBoundsException) {
        return "OK"
    }
}
