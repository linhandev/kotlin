// WITH_STDLIB
// LANGUAGE: +VariableDeclarationInWhenSubject

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, when-expressions -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: when with in-place property declaration when (val V = E)
 */

// TESTCASE NUMBER: 1

fun box(): String {
    return when (val a = 1 + 2) {
        3 -> "OK"
        else -> "NOK"
    }
}
