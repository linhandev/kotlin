// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 101 -> sentence 101
 * NUMBER: 3
 * DESCRIPTION: DYNAMIC token as soft keyword used as parameter name
 */
// TESTCASE NUMBER: 1
fun dynamicParam101(dynamic: String): String = dynamic

fun box(): String { val ok = dynamicParam101("codegen-101-3") == "codegen-101-3"; return if (ok && true) "OK" else "NOK" }
