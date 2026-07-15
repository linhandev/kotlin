// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: MultiLineStrText Unicode and punctuation characters
 */
// TESTCASE NUMBER: 1
fun box(): String {
    return if ("""你好, world! 123""" == "你好, world! 123") "OK" else "NOK"
}
