// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, built-in-annotations, kotlin-overload-resolution-by-lambda-return-type -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: OverloadResolutionByLambdaReturnType-like annotation cannot be applied to class declaration
 */

// TESTCASE NUMBER: 1
@Target(AnnotationTarget.FUNCTION)
annotation class OverloadResolutionByLambdaReturnType17775

<!WRONG_ANNOTATION_TARGET!>@OverloadResolutionByLambdaReturnType17775<!>
class BadOverloadResolution17775
