// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 82 -> sentence 82
 * NUMBER: 1
 * DESCRIPTION: IF token in if expression returning value
 */
// TESTCASE NUMBER: 1

fun box(): String {
    val v = if (true) "kw-82-82-1" else "NOK"
    return if (v == "kw-82-82-1") "OK" else "NOK"
}
