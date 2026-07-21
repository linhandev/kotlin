// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, annotation-class-declaration -> paragraph 4 -> sentence 4
 * NUMBER: 2
 * DESCRIPTION: annotation on wrong target
 */

// TESTCASE NUMBER: 1
@Target(AnnotationTarget.CLASS)
annotation class ClassOnly(val x: Int)

<!WRONG_ANNOTATION_TARGET!>@ClassOnly(1)<!>
fun wrongTarget() {}
