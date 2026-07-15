// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 98 -> sentence 98
 * NUMBER: 5
 * DESCRIPTION: NOT_IS token with NL before !is on next line
 */
// TESTCASE NUMBER: 1
fun multilineNotIs98(value: Any): String {
    return if (value
        !is String) "OK" else "NOK"
}

fun box(): String = multilineNotIs98(99)
