// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 26 -> sentence 26
 * NUMBER: 4
 * DESCRIPTION: MULT_ASSIGNMENT token used with array element arr[i] *= value
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val arr = intArrayOf(2, 3, 4)
    arr[0] *= 5
    return if (arr[0] == 10 && arr[1] == 3 && arr[2] == 4) "OK" else "NOK"
}
