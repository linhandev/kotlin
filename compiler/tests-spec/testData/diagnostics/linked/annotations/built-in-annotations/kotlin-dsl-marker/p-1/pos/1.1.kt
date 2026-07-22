// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, built-in-annotations, kotlin-dsl-marker -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: DslMarker may be applied to annotation class as DSL marker
 */

// TESTCASE NUMBER: 1
@DslMarker
@Target(AnnotationTarget.ANNOTATION_CLASS)
annotation class MyDsl17701

@MyDsl17701
annotation class HtmlTag17701

@MyDsl17701
annotation class HtmlAttr17701
