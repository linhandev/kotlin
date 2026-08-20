// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: star projection setter reports SETTER_PROJECTED_OUT
 */

// TESTCASE NUMBER: 1
class Box<T>(var value: T)

fun test(b: Box<*>) { <!SETTER_PROJECTED_OUT!>b.value<!> = 1 }
