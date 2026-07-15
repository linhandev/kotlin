// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 59 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: typeProjectionModifier varianceModifier without following type
 */

// TESTCASE NUMBER: 1

package syntax.grammar.p59.neg3

interface Holder<T>
class StrHolder : Holder<String>
val item: Holder<<!UNRESOLVED_REFERENCE!>out<!>> = StrHolder()
