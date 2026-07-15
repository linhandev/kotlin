// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 80 -> sentence 80
 * NUMBER: 3
 * DESCRIPTION: TYPEOF token as backtick-escaped property name
 */
// TESTCASE NUMBER: 1

class TypeOfProp80 {
    val `typeof`: String = "kw-80-80-3"
}

fun box(): String {
    val expected = "kw-80-80-3"
    val result = TypeOfProp80().`typeof`
    if (result != expected) return "NOK"
    return "OK"
}
