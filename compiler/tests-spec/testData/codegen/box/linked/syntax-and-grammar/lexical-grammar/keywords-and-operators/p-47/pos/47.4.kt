// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 47 -> sentence 47
 * NUMBER: 4
 * DESCRIPTION: AS_SAFE token in chained safe cast on list
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val obj: Any = listOf(1, 2, 3)
    val size = (obj as? List<*>)?.size
    return if (size == 3) "OK" else "NOK"
}
