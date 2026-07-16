// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 27 -> sentence 27
 * NUMBER: 4
 * DESCRIPTION: DIV_ASSIGNMENT token used with array element arr[i] /= value
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val arr = intArrayOf(100, 50, 25)
    arr[1] /= 5
    return if (arr[0] == 100 && arr[1] == 10 && arr[2] == 25) "OK" else "NOK"
}
