// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 101 -> sentence 101
 * NUMBER: 4
 * DESCRIPTION: DYNAMIC token as soft keyword used as property name
 */
// TESTCASE NUMBER: 1
val dynamic = "codegen-101-4"

fun box(): String {
    if (dynamic != "codegen-101-4") return "NOK"
    return "OK"
}
