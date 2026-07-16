// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: RSQUARE token closing array indexing arr[0]
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val arr = arrayOf(10, 20, 30)
    val sum = arr[0] + arr[1] + arr[2]
    return if (sum == 60) "OK" else "NOK"
}
