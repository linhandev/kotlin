// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 105 -> sentence 105
 * NUMBER: 1
 * DESCRIPTION: INTERNAL token in internal class declaration
 */
internal class InternalHolder105(val value: String)

// TESTCASE NUMBER: 1
fun box(): String = if (InternalHolder105("codegen-105-1").value == "codegen-105-1") "OK" else "NOK"
