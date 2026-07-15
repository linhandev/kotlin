// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 3 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: script packageHeader importList and statement sequence in function
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p3.pos1

import kotlin.math.abs

fun case1(): Int {
    abs(-1)
    return abs(-1)
}
