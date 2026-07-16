// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 110 -> sentence 110
 * NUMBER: 2
 * DESCRIPTION: INNER token in inner class accessing outer member
 */
// TESTCASE NUMBER: 1
class Host110 {
    private val secret = "codegen-110-2"
    inner class Reader110 {
        fun value(): String = secret
    }
}

fun box(): String = if (Host110().Reader110().value() == "codegen-110-2") "OK" else "NOK"
