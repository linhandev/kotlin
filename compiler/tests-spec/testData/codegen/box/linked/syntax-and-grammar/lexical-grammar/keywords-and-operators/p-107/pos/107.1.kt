// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 107 -> sentence 107
 * NUMBER: 1
 * DESCRIPTION: SEALED token in sealed class with subclasses
 */
sealed class Result107 {
    data class Ok107(val value: String) : Result107()
    data class Err107(val message: String) : Result107()
}

// TESTCASE NUMBER: 1
fun box(): String = when (val result: Result107 = Result107.Ok107("OK")) {
    is Result107.Ok107 -> result.value
    is Result107.Err107 -> result.message
}
