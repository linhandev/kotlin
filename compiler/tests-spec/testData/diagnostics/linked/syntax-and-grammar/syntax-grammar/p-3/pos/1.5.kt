// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 3 -> sentence 1
 * NUMBER: 5
 * DESCRIPTION: script multiple import statements and expression statements
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p3.pos5

import kotlin.math.abs
import kotlin.math.max

fun case1(): Int {
    val sum = abs(-1) + max(1, 3)
    return sum
}
