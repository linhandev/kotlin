// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, loop-statements -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: Do-while-loop evaluates body at least once before condition check
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var count = 0
    do {
        count++
    } while (false)
    return if (count == 1) "OK" else "NOK"
}
