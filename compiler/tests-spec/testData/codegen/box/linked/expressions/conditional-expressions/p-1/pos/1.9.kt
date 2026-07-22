// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, conditional-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 9
 * DESCRIPTION: if on right of plus binds as primary operand to binary plus
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val flag = true
    val r = 1 + if (flag) 2 else 3
    return if (r == 3) "OK" else "NOK"
}
