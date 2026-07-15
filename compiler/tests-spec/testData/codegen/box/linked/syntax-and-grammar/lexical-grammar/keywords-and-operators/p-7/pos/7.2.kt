// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 7 -> sentence 7
 * NUMBER: 2
 * DESCRIPTION: RSQUARE token closing nested indexing arr[arr[0]]
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val arr = arrayOf(1, 10, 20)
    return if (arr[arr[0]] == 10) "OK" else "NOK"
}
