// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 98 -> sentence 98
 * NUMBER: 1
 * DESCRIPTION: NOT_IS token in type negation check x !is String
 */
// TESTCASE NUMBER: 1
fun notIsCheck98(value: Any): String {
    return if (value !is String) "OK" else "NOK"
}

fun box(): String = notIsCheck98(42)
