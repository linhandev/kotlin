// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 66 -> sentence 66
 * NUMBER: 1
 * DESCRIPTION: IMPORT token in simple import directive with runtime usage
 */

import kotlin.collections.listOf

// TESTCASE NUMBER: 1
fun box(): String {
    val expected = "import-66-1"
    if (listOf(expected).single() != expected) return "NOK"
    return "OK"
}
