// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 21 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: annotatedDelegationSpecifier invalid annotation argument
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p21.neg2

open class Base
class Case1 : @Suppress(<!SYNTAX!>,<!> "x") Base()
