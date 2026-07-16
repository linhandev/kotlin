// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, conditional-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: if-else with both branches assigns chosen branch value to val
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val flag = true
    val x = if (flag) 1 else 2
    return if (x == 1) "OK" else "NOK"
}
