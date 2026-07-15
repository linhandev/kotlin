// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 57 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: typeProjection with varianceModifier out
 */

// TESTCASE NUMBER: 1

package syntax.grammar.p57.pos3

interface Box<T>
class IntBox : Box<Int>
fun case1(): Box<out Number> = IntBox()
