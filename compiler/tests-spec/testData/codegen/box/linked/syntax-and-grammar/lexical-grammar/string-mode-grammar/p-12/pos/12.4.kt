// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 12 -> sentence 12
 * NUMBER: 4
 * DESCRIPTION: MultiLineStrText blank lines and whitespace preserved
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val text = """
        first

        third
        """
    return if (text == "\n        first\n\n        third\n        ") "OK" else "NOK"
}
