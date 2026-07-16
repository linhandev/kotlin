// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 113 -> sentence 113
 * NUMBER: 4
 * DESCRIPTION: INLINE token in member inline function
 */
// TESTCASE NUMBER: 1
class InlineHolder113 {
    inline fun compute113(value: Int): Int = value + 1
}

fun box(): String = if (InlineHolder113().compute113(41) == 42) "OK" else "NOK"
