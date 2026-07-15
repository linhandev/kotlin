// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 26 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: typeConstraint invalid bound type hard keyword class
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p26.neg3

fun case1(): String {
    val bound = 1
    val<!SYNTAX!><!> class <!SYNTAX!>=<!> <!SYNTAX!>bound<!>
    return "OK"
}
