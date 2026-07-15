// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: RCURL token closing function body } 
 */
// TESTCASE NUMBER: 1

fun double(x: Int): Int {
    return x * 2
}

fun box(): String {
    return if (double(21) == 42) "OK" else "NOK"
}
