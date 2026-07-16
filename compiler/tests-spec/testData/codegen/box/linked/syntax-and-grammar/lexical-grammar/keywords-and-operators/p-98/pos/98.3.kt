// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 98 -> sentence 98
 * NUMBER: 3
 * DESCRIPTION: NOT_IS token with Hidden comment before !is operator
 */
// TESTCASE NUMBER: 1
fun notIsWithComment98(value: Any): String {
    return if (value /** hidden */ !is Int) "OK" else "NOK"
}

fun box(): String = notIsWithComment98("text")
