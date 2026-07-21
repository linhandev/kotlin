// LANGUAGE: +PartiallySpecifiedTypeArguments
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, declarations-with-type-parameters, underscore-type-arguments -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: underscore type arguments fail when there is no context to infer the missing type
 */

// TESTCASE NUMBER: 1
fun <T> mk(): T = TODO()

fun missingInferenceContext() {
    <!NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>mk<!><_>()
}
