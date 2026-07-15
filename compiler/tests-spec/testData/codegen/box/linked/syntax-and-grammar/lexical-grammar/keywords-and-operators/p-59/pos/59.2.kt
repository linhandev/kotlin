// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 59 -> sentence 59
 * NUMBER: 2
 * DESCRIPTION: GET token in property getter get() { block } form with runtime check
 */
// TESTCASE NUMBER: 1

class GetterBlock59(private val base: Int) {
    val doubled: Int
        get() {
            return base * 2
        }
}

fun box(): String {
    return if (GetterBlock59(21).doubled == 42) "OK" else "NOK"
}
