// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: upper bound enables Number members
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Holder<T : Number>(val v: T)

fun test(): Double = Holder(1.0).v.toDouble()

fun case1() {
    checkSubtype<Double>(test())
}
