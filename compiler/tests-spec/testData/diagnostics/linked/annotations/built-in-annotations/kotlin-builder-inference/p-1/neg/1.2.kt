// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, built-in-annotations, kotlin-builder-inference -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: BuilderInference-like annotation cannot be applied to class declaration
 */

// TESTCASE NUMBER: 1
@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY)
annotation class BuilderInference17745

<!WRONG_ANNOTATION_TARGET!>@BuilderInference17745<!>
class BadBuilderInference17745
