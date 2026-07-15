// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 108 -> sentence 108
 * NUMBER: 2
 * DESCRIPTION: ANNOTATION token in annotation class with parameter used on class
 */
// TESTCASE NUMBER: 1
annotation class Tag108(val value: String)

@Tag108("OK")
class Tagged108 {
    fun value(): String = "codegen-108-2"
}

fun box(): String = if (Tagged108().value() == "codegen-108-2") "OK" else "NOK"
