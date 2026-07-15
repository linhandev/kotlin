// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -UNSUPPORTED
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 52 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: typeReference dynamic keyword branch
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p52.pos3

@Suppress("DEPRECATION")
val value: dynamic = Any()
