// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 114 -> sentence 114
 * NUMBER: 1
 * DESCRIPTION: INFIX token in infix extension function call
 */
infix fun Int.shl114(bits: Int): Int = this shl bits

// TESTCASE NUMBER: 1
fun box(): String = if (21 shl114 1 == 42) "OK" else "NOK"
