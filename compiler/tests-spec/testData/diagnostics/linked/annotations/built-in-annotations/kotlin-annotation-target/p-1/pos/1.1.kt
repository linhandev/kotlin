// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, built-in-annotations, kotlin-annotation-target -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Target meta-annotation restricts where the annotated annotation may be applied
 */

// TESTCASE NUMBER: 1
@Target(AnnotationTarget.FUNCTION)
annotation class FunOnly17521(val value: Int)

@FunOnly17521(1)
fun ok17521() {}
