// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 5 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: fileAnnotation multiple separate file annotations
 */

// TESTCASE NUMBER: 1
@file:JvmName("FileAnnotationPos4")
@file:Suppress("UNUSED_VARIABLE")

package syntax.grammar.p5.pos4

fun case1() {
    val unused = 1
}
