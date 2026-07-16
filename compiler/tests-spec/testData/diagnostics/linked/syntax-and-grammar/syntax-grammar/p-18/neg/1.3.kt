// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 18 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: delegationSpecifiers leading comma
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p18.neg3

open class Base
class Case1 : <!SYNTAX!>,<!> Base()
