// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, conditional-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 8
 * DESCRIPTION: if (true) 1 without else used as statement has kotlin.Unit type
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val flag = true
    var executed = false
    if (flag) {
        executed = true
        1
    }
    return if (executed) "OK" else "NOK"
}
