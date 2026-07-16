// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 47 -> sentence 47
 * NUMBER: 2
 * DESCRIPTION: AS_SAFE token in when subject safe cast
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val obj: Any = "kotlin"
    val s = when (obj) {
        is String -> obj
        else -> (obj as? String) ?: return "NOK"
    }
    return if (s == "kotlin") "OK" else "NOK"
}
