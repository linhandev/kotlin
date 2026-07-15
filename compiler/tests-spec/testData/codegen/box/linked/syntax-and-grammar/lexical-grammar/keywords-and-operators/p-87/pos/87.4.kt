// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 87 -> sentence 87
 * NUMBER: 4
 * DESCRIPTION: FINALLY token after catch before return
 */
// TESTCASE NUMBER: 1
fun finallyReturn87(): String {
    try {
        throw RuntimeException()
    } catch (_: RuntimeException) {
        return "codegen-87-4"
    } finally {
        Unit
    }
}

fun box(): String { return if (finallyReturn87() == "codegen-87-4") "OK" else "NOK".let { it } }
