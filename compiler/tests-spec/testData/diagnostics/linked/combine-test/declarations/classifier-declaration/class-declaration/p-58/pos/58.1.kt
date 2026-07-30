// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 58 -> sentence 58
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 58 -> sentence 58
 * NUMBER: 1
 * DESCRIPTION: upper-bounded type parameter flows through class methods
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Pipe<T : CharSequence>(val v: T) { fun copy(): T = v }

fun test(): CharSequence = Pipe("x").copy()

fun case1() {
    checkSubtype<CharSequence>(test())
}
