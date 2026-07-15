// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 173 -> sentence 173
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 55 -> sentence 55
 * NUMBER: 2
 * DESCRIPTION: unescapedAnnotation userType qualified annotation reference
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p173.pos2

annotation class Outer {
    annotation class Nested
}

@Outer.Nested fun case1() {}
