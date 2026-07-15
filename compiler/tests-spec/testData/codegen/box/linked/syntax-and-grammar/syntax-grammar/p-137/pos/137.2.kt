// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 137 -> sentence 137
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 78 -> sentence 78
 * NUMBER: 2
 * DESCRIPTION: jumpExpression throw expression
 */
package syntax.grammar.p137.pos2

// TESTCASE NUMBER: 1
fun box(): String = if (try {
    throw IllegalStateException()
    "NOK"
} catch (_: IllegalStateException) {
    "codegen-137-2"
} == "codegen-137-2") "OK" else "NOK"
