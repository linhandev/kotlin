// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 30 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: companionObject with interface delegation
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p30.pos3

interface Marker { val tag: String }
class Case1 { companion object : Marker { override val tag: String = "m" } }
