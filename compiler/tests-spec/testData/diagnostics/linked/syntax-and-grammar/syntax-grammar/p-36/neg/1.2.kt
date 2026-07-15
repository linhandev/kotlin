// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 36 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: multiVariableDeclaration invalid simpleIdentifier in second declaration
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p36.neg2

fun case1() {
    data class Two(val a: Int, val b: Int)
    val (a, <!SYNTAX!>return<!>) = Two(1, 2)
}
