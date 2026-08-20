// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 3 -> sentence 3
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: explicit type argument conflicts with constructor argument
 */

// TESTCASE NUMBER: 1
class Box<T>(val value: T)

fun test(): Box<String> = Box<String>(<!ARGUMENT_TYPE_MISMATCH!>1<!>)
