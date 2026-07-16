// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, overview -> paragraph 1 -> sentence 1
 * NUMBER: 5
 * DESCRIPTION: annotation on annotation class declaration is valid metadata association
 */

// TESTCASE NUMBER: 1
@Target(AnnotationTarget.ANNOTATION_CLASS)
annotation class Meta17005(val value: Int)

@Meta17005(1)
annotation class AnnotatedAnnotation17005(val tag: String)
