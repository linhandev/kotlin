// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 84 -> sentence 84
 * NUMBER: 3
 * DESCRIPTION: WHEN token in when statement with block branches
 */
// TESTCASE NUMBER: 1

fun runWhen84(value: Int): String {
    when (value) {
        42 -> return "kw-84-84-3"
        else -> return "NOK"
    }
}

fun box(): String {
    val r = runWhen84(42)
    if (r != "kw-84-84-3") return "NOK"
    return "OK"
}
