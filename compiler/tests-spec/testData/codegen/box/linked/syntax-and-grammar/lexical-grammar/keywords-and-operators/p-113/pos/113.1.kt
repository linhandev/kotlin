// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 113 -> sentence 113
 * NUMBER: 1
 * DESCRIPTION: INLINE token in inline function declaration
 */
inline fun double113(value: Int): Int = value * 2

// TESTCASE NUMBER: 1
fun box(): String = if (double113(21) == 42) "OK" else "NOK"
