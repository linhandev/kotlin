// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 96 -> sentence 96
 * NUMBER: 1
 * DESCRIPTION: IS token in type check expression
 */
// TESTCASE NUMBER: 1
fun typeCheckIs96(value: Any): String {
    return if (value is String) value else "NOK"
}

fun box(): String = typeCheckIs96("OK")
