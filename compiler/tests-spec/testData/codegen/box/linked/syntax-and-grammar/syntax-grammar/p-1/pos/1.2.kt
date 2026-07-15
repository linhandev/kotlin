// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: kotlinFile runtime: fileAnnotation before packageHeader and stdlib call
 */
@file:JvmName("SyntaxGrammarP1Pos2")

package syntax.grammar.p1.pos2

import kotlin.math.max

// TESTCASE NUMBER: 1
fun box(): String = if (max(3, 5) == 5) "OK" else "NOK"
