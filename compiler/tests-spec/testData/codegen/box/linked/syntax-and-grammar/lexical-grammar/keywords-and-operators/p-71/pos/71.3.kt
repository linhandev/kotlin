// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 71 -> sentence 71
 * NUMBER: 3
 * DESCRIPTION: VAL token in destructuring val declaration
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val (first, second) = Pair("OK", "NOK")
    return if (first == "OK" && second == "NOK") "OK" else "NOK"
}
