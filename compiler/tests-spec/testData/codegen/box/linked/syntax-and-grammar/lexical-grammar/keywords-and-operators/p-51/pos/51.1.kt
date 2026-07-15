// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 51 -> sentence 51
 * NUMBER: 1
 * DESCRIPTION: RETURN_AT token in return@block from run labeled lambda
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val expected = "kw-51-51-2"
    val result = run block@ {
        return@block expected
    }
    return if (result == expected) "OK" else "NOK"
}
