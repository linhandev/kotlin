// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 113 -> sentence 113
 * NUMBER: 2
 * DESCRIPTION: INLINE token in inline function with lambda parameter
 */
inline fun run113(block: () -> String): String = block()

// TESTCASE NUMBER: 1
fun box(): String = run113 { "OK" }
