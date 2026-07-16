// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 51 -> sentence 51
 * NUMBER: 2
 * DESCRIPTION: RETURN_AT token in return@forEach from inline lambda
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val expected = "kw-51-51-3"
    var result = "NOK"
    listOf(1).forEach forEach@ {
        result = expected
        return@forEach
    }
    return if (result == expected) "OK" else "NOK"
}
