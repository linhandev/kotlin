// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 125 -> sentence 125
 * NUMBER: 2
 * DESCRIPTION: CROSSINLINE token in inline function with multiple crossinline parameters
 */
inline fun wrapCross125(crossinline first: () -> String, crossinline second: () -> String): String {
    return first() + second()
}

// TESTCASE NUMBER: 1
fun box(): String = wrapCross125({ "O" }, { "K" })
