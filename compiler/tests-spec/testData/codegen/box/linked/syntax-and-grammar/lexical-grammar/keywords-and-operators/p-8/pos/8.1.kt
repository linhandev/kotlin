// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: LCURL token used in block body of a function { return }
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val result = run {
        val a = 1
        val b = 2
        a + b
    }
    return if (result == 3) "OK" else "NOK"
}
