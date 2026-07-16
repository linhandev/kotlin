// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 172 -> sentence 172
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 173 -> sentence 173
 * NUMBER: 2
 * DESCRIPTION: annotationUseSiteTarget property field get set receiver setparam and delegate targets
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p172.pos2

@Target(AnnotationTarget.FIELD)
annotation class FieldAnn

@field:FieldAnn
var stored: Int = 1

@get:Suppress("UNUSED")
val withGet: Int
    get() = 1

@set:Suppress("UNUSED")
var withSet: Int = 1

fun @receiver:Suppress("UNUSED") String.receiverExt(): Int = length
