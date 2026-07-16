// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 115 -> sentence 115
 * NUMBER: 3
 * DESCRIPTION: EXTERNAL token in external function with multiple parameter types (Int, String, Boolean)
 */
// TESTCASE NUMBER: 1
external fun nativeSum115(a: Int, b: Int): Int
external fun nativeConcat115(a: String, b: String): String
external fun nativeAnd115(a: Boolean, b: Boolean): Boolean

fun box(): String {
    val x = 1 + 2
    val s = "Hello" + "World"
    val b = true && false
    return if (x == 3 && s == "HelloWorld" && b == false) "OK" else "NOK"
}