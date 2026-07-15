// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, whitespace-and-comments -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: CR character (U+000D) in string literal with unicode escape
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val str = "a\u000Db"
    return if (str.length == 3 && str[1].code == 13) "OK" else "NOK"
}