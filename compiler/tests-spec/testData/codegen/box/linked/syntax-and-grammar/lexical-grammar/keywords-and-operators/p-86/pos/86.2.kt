// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 86 -> sentence 86
 * NUMBER: 2
 * DESCRIPTION: CATCH token in multiple catch clauses
 */
// TESTCASE NUMBER: 1
fun multiCatch85(value: Int): String {
    try {
        if (value == 42) return "OK"
        throw IllegalArgumentException("bad")
    } catch (_: IllegalArgumentException) {
        return "NOK"
    } catch (_: Exception) {
        return "NOK"
    }
}

fun box(): String = multiCatch85(42)
