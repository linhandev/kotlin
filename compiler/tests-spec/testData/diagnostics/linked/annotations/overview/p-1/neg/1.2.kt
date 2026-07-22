// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, overview -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: annotation with CLASS target cannot be applied to function
 */

// TESTCASE NUMBER: 1
@Target(AnnotationTarget.CLASS)
annotation class ClassOnly17012(val value: Int)

<!WRONG_ANNOTATION_TARGET!>@ClassOnly17012(1)<!>
fun wrongTarget17012() {}
