// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 8 -> sentence 8
 *                declarations, declarations-with-type-parameters, type-parameter-variance -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: invariant Box Int not subtype of Box Number
 */

// TESTCASE NUMBER: 1
class Box<T>(val value: T)

fun test() {
    val b: Box<Number> = <!INITIALIZER_TYPE_MISMATCH!>Box<Int>(1)<!>
}
