// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 91 -> sentence 91
 * NUMBER: 3
 * DESCRIPTION: THROW token in throw inside try-catch
 */
fun throwCatch91(): String {
    return try {
        throw RuntimeException()
        "NOK"
    } catch (_: RuntimeException) {
        "OK"
    }
}

// TESTCASE NUMBER: 1
fun box(): String = throwCatch91()
