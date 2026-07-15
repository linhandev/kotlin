// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 92 -> sentence 92
 * NUMBER: 3
 * DESCRIPTION: RETURN token in anonymous function return
 */
// TESTCASE NUMBER: 1
fun returnLambda92(): String {
    val supplier = fun(): String {
        return "codegen-92-3"
    }
    return supplier()
}

fun box(): String = if (returnLambda92() == "codegen-92-3") "OK" else "NOK"
