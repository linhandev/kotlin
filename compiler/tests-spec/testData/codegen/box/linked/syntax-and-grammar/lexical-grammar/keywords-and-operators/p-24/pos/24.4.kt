// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 24 -> sentence 24
 * NUMBER: 4
 * DESCRIPTION: ADD_ASSIGNMENT token used with array element arr[i] += value
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val arr = intArrayOf(1, 2, 3)
    arr[1] += 10
    return if (arr[0] == 1 && arr[1] == 12 && arr[2] == 3) "OK" else "NOK"
}
