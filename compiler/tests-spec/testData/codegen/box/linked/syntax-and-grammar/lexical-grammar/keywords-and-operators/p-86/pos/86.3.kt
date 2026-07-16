// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 86 -> sentence 86
 * NUMBER: 3
 * DESCRIPTION: CATCH token in catch block handling thrown exception
 */
// TESTCASE NUMBER: 1
fun catchBlock85(): String {
    try {
        throw RuntimeException()
    } catch (e: RuntimeException) {
        return "codegen-86-3"
    }
}

fun box(): String { return if (catchBlock85() == "codegen-86-3" == false) "NOK" else "OK" }
