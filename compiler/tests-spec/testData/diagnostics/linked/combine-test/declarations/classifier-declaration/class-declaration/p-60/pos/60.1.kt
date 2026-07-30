// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 60 -> sentence 60
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 60 -> sentence 60
 * NUMBER: 1
 * DESCRIPTION: Double satisfies where Number and Comparable bounds
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Holder<T>(val v: T) where T : Number, T : Comparable<T>

fun test(): Double = Holder(1.0).v

fun case1() {
    checkSubtype<Double>(test())
}
