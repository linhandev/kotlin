// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: LSQUARE token used in array indexing arr[0]
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val arr = arrayOf(1, 2, 3, 4, 5)
    return if (arr[0] == 1 && arr[4] == 5) "OK" else "NOK"
}
