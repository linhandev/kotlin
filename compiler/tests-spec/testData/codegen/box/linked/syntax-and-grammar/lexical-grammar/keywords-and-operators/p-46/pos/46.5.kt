// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 46 -> sentence 46
 * NUMBER: 5
 * DESCRIPTION: EXCL_EQEQ token in string literal "!=="
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val op = "!=="
    if (op.first() != '!') return "NOK"
    return if (op.endsWith("==")) "OK" else "NOK"
}
