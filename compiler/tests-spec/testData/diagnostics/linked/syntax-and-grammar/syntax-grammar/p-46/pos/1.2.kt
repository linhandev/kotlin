// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 46 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: secondaryConstructor with super delegation
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p46.pos2

open class Base(val value: Int)
class Case1 : Base {
    constructor(text: String) : super(text.length) {
        val size: Int = text.length
    }
}
