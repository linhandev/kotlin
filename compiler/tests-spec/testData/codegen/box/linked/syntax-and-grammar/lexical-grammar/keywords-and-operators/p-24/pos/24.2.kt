// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 24 -> sentence 24
 * NUMBER: 2
 * DESCRIPTION: ADD_ASSIGNMENT token used in loop accumulation sum += i
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var sum = 0
    for (i in 1..5) {
        sum += i
    }
    return if (sum == 15) "OK" else "NOK"
}
