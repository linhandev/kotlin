// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 46 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: secondaryConstructor with functionValueParameters
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p46.pos3

class Case1(val value: Int) {
    constructor(prefix: String, number: Int) : this(number) {
        val label: String = prefix
    }
}
