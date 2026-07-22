// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, annotation-targets -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: type parameter annotation target is valid
 */

// TESTCASE NUMBER: 1
@Target(AnnotationTarget.TYPE_PARAMETER)
annotation class TypeParam17304(val value: Int)

class Generic17304<@TypeParam17304(1) T>
