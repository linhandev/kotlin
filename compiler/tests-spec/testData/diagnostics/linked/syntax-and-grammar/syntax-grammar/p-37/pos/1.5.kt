// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 37 -> sentence 1
 * NUMBER: 5
 * DESCRIPTION: propertyDeclaration with propertyDelegate
 */

// TESTCASE NUMBER: 1

package syntax.grammar.p37.pos5

val value: Int by lazy { 1 }
