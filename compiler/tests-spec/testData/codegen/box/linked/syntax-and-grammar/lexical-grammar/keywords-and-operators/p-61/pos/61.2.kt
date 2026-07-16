// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 61 -> sentence 61
 * NUMBER: 2
 * DESCRIPTION: RECEIVER token in use-site annotation @receiver:Suppress on extension property
 */
// TESTCASE NUMBER: 1

val @receiver:Suppress("UNUSED_PARAMETER") Int.code: String
    get() = "kw-61-61-2"

fun box(): String {
    val expected = "kw-61-61-2"
    val result = 1.code
    if (result != expected) return "NOK"
    return "OK"
}
