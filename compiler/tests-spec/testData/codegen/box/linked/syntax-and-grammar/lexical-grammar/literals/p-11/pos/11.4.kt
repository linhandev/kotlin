// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 11 -> sentence 11
 * NUMBER: 4
 * DESCRIPTION: NullLiteral null token
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val n: Nothing? = null
    return if (n == null) "OK" else "NOK"
}
