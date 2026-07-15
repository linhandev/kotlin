// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 36 -> sentence 36
 * NUMBER: 1
 * DESCRIPTION: AT_POST_WS token in labeled for loop label@ and break@label
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var hit = 0
    label@ for (i in 1..2) {
        if (i == 2) { hit = i; break@label }
    }
    return if (hit == 2) "OK" else "NOK"
}
