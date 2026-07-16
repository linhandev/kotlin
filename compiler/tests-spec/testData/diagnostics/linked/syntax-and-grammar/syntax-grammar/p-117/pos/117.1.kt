// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 117 -> sentence 117
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 114 -> sentence 114
 * NUMBER: 1
 * DESCRIPTION: multiLineStringContent multiline text
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p117.pos1

fun case1() { val s = """
    multiline
    text
    """.trimIndent(); check(s.contains("multiline")) }
