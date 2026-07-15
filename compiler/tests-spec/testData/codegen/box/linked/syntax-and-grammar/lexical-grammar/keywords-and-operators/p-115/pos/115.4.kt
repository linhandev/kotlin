// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 115 -> sentence 115
 * NUMBER: 4
 * DESCRIPTION: EXTERNAL token in external function with block body and return expression
 */
// TESTCASE NUMBER: 1
external fun nativeCompute115(x: Int): String

fun compute115(x: Int): String {
    return if (x > 0) "positive" else "non-positive"
}

fun box(): String {
    val result1 = compute115(10)
    val result2 = compute115(-5)
    return if (result1 == "positive" && result2 == "non-positive") "OK" else "NOK"
}
