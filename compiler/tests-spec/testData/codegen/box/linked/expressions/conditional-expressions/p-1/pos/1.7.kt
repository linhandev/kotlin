// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, conditional-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 7
 * DESCRIPTION: if (true) 1 else 2L has Number type with Int value 1
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val flag = true
    val x: Number = if (flag) 1 else 2L
    return if (x == 1) "OK" else "NOK"
}
