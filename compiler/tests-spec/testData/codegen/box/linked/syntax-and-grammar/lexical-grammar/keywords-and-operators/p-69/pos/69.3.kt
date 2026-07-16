// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 69 -> sentence 69
 * NUMBER: 3
 * DESCRIPTION: FUN token in extension function declaration
 */
// TESTCASE NUMBER: 1

fun Int.ext69(): Int = this + 1

fun box(): String = if (2.ext69() == 3) "OK" else "NOK"

