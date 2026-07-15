// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 35 -> sentence 35
 * NUMBER: 1
 * DESCRIPTION: AT_NO_WS token in class annotation @Deprecated without space after @
 */
// TESTCASE NUMBER: 1

@Deprecated("legacy")
class Legacy(val value: Int = 1)

fun box(): String {
    return if (Legacy().value == 1) "OK" else "NOK"
}
