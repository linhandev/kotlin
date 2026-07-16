// LANGUAGE: +PartiallySpecifiedTypeArguments
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, declarations-with-type-parameters, underscore-type-arguments -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: underscore type arguments fail when inference cannot succeed
 */

// TESTCASE NUMBER: 1
fun <T> foo(): T = TODO()

fun bar() {
    foo<!WRONG_NUMBER_OF_TYPE_ARGUMENTS!><String, _><!>()
}
