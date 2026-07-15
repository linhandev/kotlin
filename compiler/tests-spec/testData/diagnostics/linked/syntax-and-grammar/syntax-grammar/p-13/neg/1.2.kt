// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 13 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: classDeclaration hard keyword return used as class name
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p13.neg2

class <!SYNTAX!>return<!>
