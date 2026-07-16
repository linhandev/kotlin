// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 29 -> sentence 29
 * NUMBER: 3
 * DESCRIPTION: ARROW token used in when entry 1 -> "one"
 */
// TESTCASE NUMBER: 1

fun label(n: Int): String = when (n) {
    1 -> "one"
    2 -> "two"
    else -> "other"
}

fun box(): String {
    return if (label(1) == "one" && label(2) == "two" && label(3) == "other") "OK" else "NOK"
}
