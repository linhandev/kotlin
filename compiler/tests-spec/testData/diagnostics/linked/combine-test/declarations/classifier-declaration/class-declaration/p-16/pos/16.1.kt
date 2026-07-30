// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: generic class member preserves its type argument when returning a new instance
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class PairBox<T>(val first: T, val second: T) {
    fun swapped(): PairBox<T> = PairBox(second, first)
}

fun case1(box: PairBox<String>) {
    checkSubtype<PairBox<String>>(box.swapped())
    checkSubtype<String>(box.swapped().first)
}
