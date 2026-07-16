// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 44 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: parameter NL after colon before type
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p44.pos3

fun case1(value:
    Int, suffix: String): String = "$value$suffix"
