// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 11 -> sentence 11
 *                declarations, declarations-with-type-parameters, type-parameter-variance -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: in variance allows InBox of Number to be assigned to InBox of Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class InBox<in T> {
    fun accept(x: T) {}
}

fun case1() {
    val b: InBox<Int> = InBox<Number>()
    checkSubtype<InBox<Int>>(b)
    b.accept(1)
}
