// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 170 -> sentence 170
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 172 -> sentence 172
 * syntax-and-grammar, syntax-grammar -> paragraph 173 -> sentence 173
 * NUMBER: 2
 * DESCRIPTION: singleAnnotation annotation use site targets
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p170.pos2

@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class ParamAnn

@get:Suppress("unused")
var case1: Int = 1

class Case2(@param:ParamAnn val x: Int)
