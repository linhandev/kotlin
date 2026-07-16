// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 73 -> sentence 73
 * NUMBER: 5
 * DESCRIPTION: TYPE_ALIAS token as backtick-escaped identifier fun `typealias`
 */
// TESTCASE NUMBER: 1

fun `typealias`(): String = "kw-pos-73-5"

fun box(): String {
    val r = `typealias`().also { }; return if (r == "kw-pos-73-5") "OK" else "NOK"
}
