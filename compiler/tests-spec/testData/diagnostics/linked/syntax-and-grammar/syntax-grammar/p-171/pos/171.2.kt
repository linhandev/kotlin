// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 171 -> sentence 171
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 172 -> sentence 172
 * syntax-and-grammar, syntax-grammar -> paragraph 173 -> sentence 173
 * NUMBER: 2
 * DESCRIPTION: multiAnnotation with annotation use site target prefix
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p171.pos2

@get:[Suppress("unused") Deprecated("d")]
var case1: Int = 1
