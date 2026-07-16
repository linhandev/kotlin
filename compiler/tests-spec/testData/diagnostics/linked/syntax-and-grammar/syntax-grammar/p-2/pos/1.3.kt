// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 2 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: kotlinFile packageHeader followed by importList
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p2.pos3

import kotlin.math.min

fun case1() {
    val x = min(1, 2)
}