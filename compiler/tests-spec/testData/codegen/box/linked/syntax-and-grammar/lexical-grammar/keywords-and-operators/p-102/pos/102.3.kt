// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 102 -> sentence 102
 * NUMBER: 3
 * DESCRIPTION: PUBLIC token in public property declaration
 */
public val publicVal102: String = "codegen-102-3"
// TESTCASE NUMBER: 1
fun box(): String = if (publicVal102 == "codegen-102-3") "OK" else "NOK"
