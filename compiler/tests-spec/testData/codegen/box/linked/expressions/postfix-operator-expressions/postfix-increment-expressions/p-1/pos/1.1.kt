// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, postfix-operator-expressions, postfix-increment-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: postfix increment returns old value and assigns inc result
 */

// TESTCASE NUMBER: 1

fun box(): String {
    var i = 1
    val result = i++
    if (result != 1) return "NOK"
    if (i != 2) return "NOK"
    return "OK"
}
