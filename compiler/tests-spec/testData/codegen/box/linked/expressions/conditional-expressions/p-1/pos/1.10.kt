// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, conditional-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 10
 * DESCRIPTION: if on left side of plus has lowest precedence so else binds to addition not if
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val flag = true
    val l = if (flag) 1 else 2 + 3
    return if (l == 1) "OK" else "NOK"
}
