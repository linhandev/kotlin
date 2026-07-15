// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 38 -> sentence 38
 * NUMBER: 1
 * DESCRIPTION: AT_BOTH_WS token in file annotation with block comments before and after @
 */

/* before file annotation */
@file:Suppress("WARNING")
/* after file annotation */
fun marker38_2(): String = "file-annotation"

// TESTCASE NUMBER: 1
fun box(): String {
    return if (marker38_2().length == 15) "OK" else "NOK"
}
