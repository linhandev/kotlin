// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 52 -> sentence 52
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 52 -> sentence 52
 * NUMBER: 1
 * DESCRIPTION: nested where clause may reference outer type parameter
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Outer<T> { inner class Inner<U> where U : T { fun cast(u: U): T = u } }

fun test(): Number = Outer<Number>().Inner<Int>().cast(1)

fun case1() {
    checkSubtype<Number>(test())
}
