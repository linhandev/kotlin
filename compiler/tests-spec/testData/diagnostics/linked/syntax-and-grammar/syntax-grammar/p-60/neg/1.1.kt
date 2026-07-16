// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 60 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: functionType missing arrow token
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p60.neg1

val handler: (String)<!SYNTAX!>Unit =<!> <!FUNCTION_DECLARATION_WITH_NO_NAME!><!SYNTAX!><!>{}<!>
