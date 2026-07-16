// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 92 -> sentence 92
 * NUMBER: 4
 * DESCRIPTION: RETURN token in non-local return from inline block
 */
// TESTCASE NUMBER: 1
fun returnBlock92(): String {
    run {
        return "codegen-92-4"
    }
    return "NOK"
}

fun box(): String { var passed = false; if (returnBlock92() == "codegen-92-4") passed = true; return if (passed) "OK" else "NOK" }
