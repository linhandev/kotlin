// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 3 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: script NL blank lines between package import and statements
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p3.pos4


import kotlin.math.min


fun case1(): Int = min(3, 5)
