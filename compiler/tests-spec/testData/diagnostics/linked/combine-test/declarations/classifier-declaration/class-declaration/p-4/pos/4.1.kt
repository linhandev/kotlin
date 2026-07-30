// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: multi type-parameter class construction
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class PairBox<A, B>(val first: A, val second: B)

fun test(): PairBox<Int, String> = PairBox(1, "a")

fun case1() {
    checkSubtype<PairBox<Int, String>>(test())
}
