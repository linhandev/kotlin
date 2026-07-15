// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 159 -> sentence 159
 * NUMBER: 2
 * DESCRIPTION: visibilityModifier public internal and protected visibility
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p159.pos2

public fun case1() {}

internal fun case2() {}

open class B {
    protected fun case3() {}
}
