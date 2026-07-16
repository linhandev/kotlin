// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 114 -> sentence 114
 * NUMBER: 3
 * DESCRIPTION: INFIX token in infix range helper function
 */
infix fun Int.until114(end: Int): IntRange = this..end

// TESTCASE NUMBER: 1
fun box(): String = if (40 in 1 until114 42) "OK" else "NOK"
