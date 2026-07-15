// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 108 -> sentence 108
 * NUMBER: 4
 * DESCRIPTION: ANNOTATION token in property-target annotation class
 */
// TESTCASE NUMBER: 1
@Target(AnnotationTarget.PROPERTY)
annotation class PropTag108

class PropHolder108 {
    @PropTag108
    val token: String = "codegen-108-4"
}

fun box(): String = if (PropHolder108().token == "codegen-108-4") "OK" else "NOK"
