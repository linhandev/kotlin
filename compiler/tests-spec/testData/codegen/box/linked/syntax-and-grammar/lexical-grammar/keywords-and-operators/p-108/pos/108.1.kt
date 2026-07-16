// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 108 -> sentence 108
 * NUMBER: 1
 * DESCRIPTION: ANNOTATION token in annotation class declaration
 */
// TESTCASE NUMBER: 1
annotation class Marker108

@Marker108
class Annotated108 {
    fun value(): String = "codegen-108-1"
}

fun box(): String = if (Annotated108().value() == "codegen-108-1") "OK" else "NOK"
