// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 64 -> sentence 64
 * NUMBER: 2
 * DESCRIPTION: DELEGATE token in bracket use-site @delegate:[Suppress] on delegated property
 */
// TESTCASE NUMBER: 1

class DelegateBracket64 {
    @delegate:[Suppress("UNUSED")]
    val code: Int by lazy { 42 }
}

fun box(): String {
    return if (DelegateBracket64().code == 42) "OK" else "NOK"
}
