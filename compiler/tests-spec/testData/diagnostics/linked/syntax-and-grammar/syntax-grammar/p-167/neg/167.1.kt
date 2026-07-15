// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 167 -> sentence 167
 * NUMBER: 1
 * DESCRIPTION: reificationModifier reified without inline function
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p167.neg1

class Box<<!REIFIED_TYPE_PARAMETER_NO_INLINE!>reified<!> T>
