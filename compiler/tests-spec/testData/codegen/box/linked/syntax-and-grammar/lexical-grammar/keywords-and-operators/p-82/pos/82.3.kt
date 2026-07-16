// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 82 -> sentence 82
 * NUMBER: 3
 * DESCRIPTION: IF token in nested if expression chain
 */
// TESTCASE NUMBER: 1

fun box(): String {
    val v = if (1 < 2) if (2 < 3) "kw-82-82-3" else "NOK" else "NOK"
    return if (v == "kw-82-82-3") "OK" else "NOK"
}
