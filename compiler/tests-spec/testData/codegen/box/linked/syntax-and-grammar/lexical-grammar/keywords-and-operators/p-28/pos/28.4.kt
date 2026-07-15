// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 28 -> sentence 28
 * NUMBER: 4
 * DESCRIPTION: MOD_ASSIGNMENT token used with array element arr[i] %= value
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val arr = intArrayOf(10, 17, 23)
    arr[1] %= 5
    return if (arr[0] == 10 && arr[1] == 2 && arr[2] == 23) "OK" else "NOK"
}
