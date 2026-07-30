// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 43 -> sentence 43
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 43 -> sentence 43
 * NUMBER: 1
 * DESCRIPTION: where A colon B holds for Int and Number
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Rel<A, B> where A : B { val a: A? = null }

fun test(): Rel<Int, Number> = Rel()

fun case1() {
    checkSubtype<Rel<Int, Number>>(test())
}
