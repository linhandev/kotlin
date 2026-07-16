// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 5 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: fileAnnotation @file:JvmName single annotation
 */

// TESTCASE NUMBER: 1
@file:JvmName("FileAnnotationPos1")

package syntax.grammar.p5.pos1

val case1: Int = 1
