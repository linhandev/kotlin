// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 25 -> sentence 25
 * NUMBER: 4
 * DESCRIPTION: SUB_ASSIGNMENT token used with array element arr[i] -= value
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val arr = intArrayOf(10, 20, 30)
    arr[2] -= 5
    return if (arr[0] == 10 && arr[1] == 20 && arr[2] == 25) "OK" else "NOK"
}
