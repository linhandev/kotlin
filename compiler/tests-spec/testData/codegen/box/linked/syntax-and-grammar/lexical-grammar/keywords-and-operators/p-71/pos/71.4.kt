// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 71 -> sentence 71
 * NUMBER: 4
 * DESCRIPTION: VAL token in local val inside box function
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val local71 = "kw-71-71-4"
    return if (local71 == "kw-71-71-4") "OK" else "NOK"
}
