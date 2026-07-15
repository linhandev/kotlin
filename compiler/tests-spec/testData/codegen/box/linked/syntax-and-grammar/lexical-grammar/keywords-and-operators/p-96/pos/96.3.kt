// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 96 -> sentence 96
 * NUMBER: 3
 * DESCRIPTION: IS token in nullable type check is String?
 */
// TESTCASE NUMBER: 1
fun nullableIs96(value: Any?): String {
    return if (value is String?) value ?: "NOK" else "NOK"
}

fun box(): String = nullableIs96("OK")
