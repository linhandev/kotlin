// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, postfix-operator-expressions, postfix-decrement-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: postfix decrement returns old value and assigns dec result
 */

// TESTCASE NUMBER: 1

fun box(): String {
    var i = 2
    val result = i--
    if (result != 2) return "NOK"
    if (i != 1) return "NOK"
    return "OK"
}
