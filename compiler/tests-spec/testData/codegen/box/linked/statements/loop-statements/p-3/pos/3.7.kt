// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, loop-statements -> paragraph 3 -> sentence 3
 * NUMBER: 7
 * DESCRIPTION: for (v in emptyList<Int>()) leaves count == 0 at runtime
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var count = 0
    for (v in emptyList<Int>()) {
        count++
    }
    return if (count == 0) "OK" else "NOK"
}
