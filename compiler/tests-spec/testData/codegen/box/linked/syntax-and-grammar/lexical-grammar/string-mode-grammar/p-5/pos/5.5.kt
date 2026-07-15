// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 5 -> sentence 5
 * NUMBER: 5
 * DESCRIPTION: LineStrRef $count with longer identifier name
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val count = 3
    return when {
        "total=$count" == "total=3" -> "OK"
        else -> "NOK"
    }
}
