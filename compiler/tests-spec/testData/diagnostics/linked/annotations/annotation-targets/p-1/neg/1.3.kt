// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, annotation-targets -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: annotation with CLASS target cannot be applied to function parameter
 */

// TESTCASE NUMBER: 1
@Target(AnnotationTarget.CLASS)
annotation class ClassOnly17313(val value: Int)

fun wrongParamTarget17313(<!WRONG_ANNOTATION_TARGET!>@ClassOnly17313(1)<!> param17313: Int) {}
