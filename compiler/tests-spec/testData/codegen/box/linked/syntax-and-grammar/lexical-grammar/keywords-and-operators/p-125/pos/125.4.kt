// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 125 -> sentence 125
 * NUMBER: 4
 * DESCRIPTION: CROSSINLINE token in member inline function crossinline parameter
 */
// TESTCASE NUMBER: 1
class CrossInlineHolder125 {
    inline fun apply125(crossinline block: () -> String): String = block()
}

fun box(): String {
    val expected = "crossinline-125-4"
    val result = CrossInlineHolder125().apply125 { expected }
    if (result != expected) return "NOK"
    return "OK"
}
