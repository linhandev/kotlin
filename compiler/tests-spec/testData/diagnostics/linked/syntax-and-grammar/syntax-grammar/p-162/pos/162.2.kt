// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 162 -> sentence 162
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 160 -> sentence 160
 * syntax-and-grammar, syntax-grammar -> paragraph 169 -> sentence 169
 * NUMBER: 2
 * DESCRIPTION: typeParameterModifier variance and annotation branches
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p162.pos2

@Target(AnnotationTarget.TYPE_PARAMETER)
annotation class Ann

class OutCase<out T>

interface InCase<in T>

class AnnCase<@Ann T>
