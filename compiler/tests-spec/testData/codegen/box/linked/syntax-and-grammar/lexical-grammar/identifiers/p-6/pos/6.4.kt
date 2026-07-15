// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, identifiers -> paragraph 6 -> sentence 6
 * NUMBER: 4
 * DESCRIPTION: Soft keyword param used as local variable name
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val param = 4
    return when (param) { 4 -> "OK"; else -> "NOK" }
}
