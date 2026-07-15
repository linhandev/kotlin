// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 62 -> sentence 62
 * NUMBER: 1
 * DESCRIPTION: PARAM token in use-site annotation @param:Suppress on constructor parameter
 */
// TESTCASE NUMBER: 1

class ParamCtor62(@param:Suppress("UNUSED_PARAMETER") val id: Int)

fun box(): String {
    return if (ParamCtor62(7).id == 7) "OK" else "NOK"
}
