// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 32 -> sentence 32
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 32 -> sentence 32
 * NUMBER: 1
 * DESCRIPTION: Number upper bound enables arithmetic on member result
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class NumBox<T : Number>(val v: T) { fun plusOne() = v.toDouble() + 1.0 }

fun case1() {
    checkSubtype<Double>(NumBox(1).plusOne())
}
