// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, annotation-targets -> paragraph 1 -> sentence 1
 * NUMBER: 5
 * DESCRIPTION: local property annotation target is valid
 */

// TESTCASE NUMBER: 1
@Target(AnnotationTarget.LOCAL_VARIABLE)
annotation class Local17305(val value: Int)

fun localProperty17305() {
    @Local17305(1)
    val local17305 = 1
}
