// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 47 -> sentence 47
 * NUMBER: 3
 * DESCRIPTION: AS_SAFE token with elvis fallback
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val obj: Any = 7
    val value = (obj as? String) ?: "fallback"
    return if (value == "fallback") "OK" else "NOK"
}
