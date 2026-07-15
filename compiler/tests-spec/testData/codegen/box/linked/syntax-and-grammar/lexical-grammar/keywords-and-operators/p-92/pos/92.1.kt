// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 92 -> sentence 92
 * NUMBER: 1
 * DESCRIPTION: RETURN token in return with value
 */
// TESTCASE NUMBER: 1
fun returnUnit92(): String {
    return "returned-92-1"
}

fun box(): String { val ok = returnUnit92() == "returned-92-1"; if (ok) { return "OK" }; return "NOK" }
