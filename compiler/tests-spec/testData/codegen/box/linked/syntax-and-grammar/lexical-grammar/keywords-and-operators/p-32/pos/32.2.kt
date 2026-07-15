// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 32 -> sentence 32
 * NUMBER: 2
 * DESCRIPTION: COLONCOLON token used in top-level function reference
 */
// TESTCASE NUMBER: 1

fun helper(x: Int): Int = x

fun box(): String {
    val ref = ::helper
    return if (ref(21) == 21) "OK" else "NOK"
}
