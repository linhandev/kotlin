// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 156 -> sentence 156
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 169 -> sentence 169
 * NUMBER: 2
 * DESCRIPTION: typeModifier annotation on type
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p156.pos2

@Target(AnnotationTarget.TYPE)
annotation class TypeAnn

val case1: @TypeAnn Int = 1
