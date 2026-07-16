// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 125 -> sentence 125
 * NUMBER: 3
 * DESCRIPTION: CROSSINLINE token in crossinline lambda returning Int
 */
inline fun invokeCross125(crossinline action: () -> Int): Int = action()

// TESTCASE NUMBER: 1
fun box(): String = if (invokeCross125 { 42 } == 42) "OK" else "NOK"
