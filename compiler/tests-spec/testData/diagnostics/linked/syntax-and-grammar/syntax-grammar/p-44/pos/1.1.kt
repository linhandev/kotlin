// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 44 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: parameter catch clause simpleIdentifier colon type
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p44.pos1

fun case1(): Int {
    try {
        return 1
    } catch (e: Exception) {
        return 0
    }
}
