// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 35 -> sentence 35
 * NUMBER: 2
 * DESCRIPTION: AT_NO_WS token in function annotation @Suppress without space after @
 */

@Suppress("UNUSED_VARIABLE")
// TESTCASE NUMBER: 1
fun box(): String {
    val unused = 42
    val used = unused + 1
    return if (used == 43) "OK" else "NOK"
}
