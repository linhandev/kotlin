// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 98 -> sentence 98
 * NUMBER: 4
 * DESCRIPTION: NOT_IS token distinguished from EXCL ! in same function
 */
// TESTCASE NUMBER: 1
fun exclVsNotIs98(flag: Boolean, value: Any): String {
    if (!flag) return "NOK"
    return if (value !is String) "OK" else "NOK"
}

fun box(): String = exclVsNotIs98(true, 1)
