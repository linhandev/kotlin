// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, built-in-annotations, kotlin-annotation-target -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Target meta-annotation cannot be applied to regular class
 */

// TESTCASE NUMBER: 1
<!WRONG_ANNOTATION_TARGET!>@Target(AnnotationTarget.CLASS)<!>
class RegularClass17531
