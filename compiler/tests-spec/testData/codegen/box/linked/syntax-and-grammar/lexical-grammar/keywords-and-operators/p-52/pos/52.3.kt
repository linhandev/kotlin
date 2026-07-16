// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 52 -> sentence 52
 * NUMBER: 3
 * DESCRIPTION: CONTINUE_AT token in continue@outer from nested while loops
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var x = 0
    outer@ while (x < 3) {
        x++
        var y = 0
        while (y < 3) {
            y++
            if (y == 2) {
                continue@outer
            }
        }
    }
    return if (x == 3) "OK" else "NOK"
}
