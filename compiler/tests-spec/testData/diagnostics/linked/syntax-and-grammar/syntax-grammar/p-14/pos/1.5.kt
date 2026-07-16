// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 14 -> sentence 1
 * NUMBER: 5
 * DESCRIPTION: primaryConstructor with modifier on constructor
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p14.pos5

class Case1 private constructor(val value: Int) {
    companion object {
        fun create(value: Int) = Case1(value)
    }
}
