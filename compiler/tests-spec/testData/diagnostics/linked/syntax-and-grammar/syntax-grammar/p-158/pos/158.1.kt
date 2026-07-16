// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 158 -> sentence 158
 * NUMBER: 1
 * DESCRIPTION: memberModifier override member modifier
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p158.pos1

open class B { open fun f() {} }
class D : B() { override fun f() {} }
