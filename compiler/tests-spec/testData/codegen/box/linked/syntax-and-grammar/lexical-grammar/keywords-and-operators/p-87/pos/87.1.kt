// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 87 -> sentence 87
 * NUMBER: 1
 * DESCRIPTION: FINALLY token in try-finally statement
 */
// TESTCASE NUMBER: 1
fun finally87(): String {
    var result = "NOK"
    try {
        result = "OK"
    } finally {
        if (result != "OK") error("unexpected")
    }
    return result
}

fun box(): String = finally87()
