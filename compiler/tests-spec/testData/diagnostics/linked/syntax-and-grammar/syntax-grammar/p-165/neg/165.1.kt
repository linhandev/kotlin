// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 165 -> sentence 165
 * NUMBER: 1
 * DESCRIPTION: inheritanceModifier abstract on object
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p165.neg1

<!WRONG_MODIFIER_TARGET!>abstract<!> object Case1
