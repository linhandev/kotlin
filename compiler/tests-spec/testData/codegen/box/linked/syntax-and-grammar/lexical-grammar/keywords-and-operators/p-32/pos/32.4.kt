// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 32 -> sentence 32
 * NUMBER: 4
 * DESCRIPTION: COLONCOLON token used in constructor reference ::Box
 */
// TESTCASE NUMBER: 1

class Box(val value: Int)

fun box(): String {
    val factory: (Int) -> Box = ::Box
    return if (factory(42).value == 42) "OK" else "NOK"
}
