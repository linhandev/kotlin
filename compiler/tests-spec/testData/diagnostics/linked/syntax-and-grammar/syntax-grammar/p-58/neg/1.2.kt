// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 58 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: typeProjectionModifiers missing type after modifier
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p58.neg2

interface Box<T>
val value: Box<<!SYNTAX!><!>> = object : Box<Int> {}
