// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 4 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: shebangLine optional fileAnnotation NL packageHeader
 */

// TESTCASE NUMBER: 1
@file:JvmName("ShebangPos3")

package syntax.grammar.p4.pos3

val case1: Int = 3
