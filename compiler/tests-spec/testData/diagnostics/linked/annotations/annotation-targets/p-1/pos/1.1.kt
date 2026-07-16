// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, annotation-targets -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Target meta-annotation with CLASS and FUNCTION allows annotation on both targets
 */

// TESTCASE NUMBER: 1
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class ClassAndFunction17301(val value: Int)

@ClassAndFunction17301(1)
class TargetedClass17301 {
    @ClassAndFunction17301(2)
    fun targetedFunction17301() {}
}
