// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 47 -> sentence 47
 * NUMBER: 1
 * DESCRIPTION: AS_SAFE token in safe cast Any to Int
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val obj: Any = 42
    val n = obj as? Int
    return if (n == 42) "OK" else "NOK"
}
