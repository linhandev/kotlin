// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, built-in-annotations, kotlin-annotation-retention -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Retention meta-annotation cannot be applied to function declaration
 */

// TESTCASE NUMBER: 1
<!WRONG_ANNOTATION_TARGET!>@Retention(AnnotationRetention.SOURCE)<!>
fun badRetentionTarget17511() {}
