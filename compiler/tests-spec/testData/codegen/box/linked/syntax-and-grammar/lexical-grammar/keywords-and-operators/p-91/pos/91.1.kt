// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 91 -> sentence 91
 * NUMBER: 1
 * DESCRIPTION: THROW token in throw RuntimeException expression
 */
// TESTCASE NUMBER: 1
fun throw91(flag: Boolean): String {
    if (flag) return "OK"
    throw RuntimeException("fail")
}

fun box(): String = throw91(true)
