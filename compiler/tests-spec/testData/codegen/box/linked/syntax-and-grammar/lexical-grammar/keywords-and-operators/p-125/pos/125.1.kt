// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 125 -> sentence 125
 * NUMBER: 1
 * DESCRIPTION: CROSSINLINE token in inline function crossinline parameter
 */
inline fun runCross125(crossinline block: () -> String): String = block()

// TESTCASE NUMBER: 1
fun box(): String = runCross125 { "OK" }
