// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 43 -> sentence 43
 * NUMBER: 3
 * DESCRIPTION: LE token in while condition while (i <= 3)
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var i = 0
    var sum = 0
    while (i <= 3) {
        sum += i
        i++
    }
    return if (sum == 6) "OK" else "NOK"
}
