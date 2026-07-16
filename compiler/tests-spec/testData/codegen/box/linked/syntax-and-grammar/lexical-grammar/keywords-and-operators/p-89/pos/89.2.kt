// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 89 -> sentence 89
 * NUMBER: 2
 * DESCRIPTION: DO token in do-while with block body executed once
 */
// TESTCASE NUMBER: 1
fun doWhileBlock89(): String {
    var token = "NOK"
    do {
        token = "codegen-89-2"
    } while (false)
    return token
}

fun box(): String { val r = doWhileBlock89() == "codegen-89-2"; return if (!r) "NOK" else "OK" }
