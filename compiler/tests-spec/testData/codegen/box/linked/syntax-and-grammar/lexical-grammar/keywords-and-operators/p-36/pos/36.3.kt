// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 36 -> sentence 36
 * NUMBER: 3
 * DESCRIPTION: AT_POST_WS token in while label outer@ with break@outer
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var count = 0
    outer@ while (count < 5) {
        count++
        if (count == 3) {
            break@outer
        }
    }
    return if (count == 3) "OK" else "NOK"
}
