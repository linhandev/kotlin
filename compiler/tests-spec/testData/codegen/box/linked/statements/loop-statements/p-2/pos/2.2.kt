// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, loop-statements -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: Do-while-loop repeats body while condition is true after each iteration
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var sum = 0
    var i = 0
    do {
        sum += i
        i++
    } while (i < 3)
    return if (sum == 3) "OK" else "NOK"
}
