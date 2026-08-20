// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 14 -> sentence 14
 *                expressions, prefix-expressions, prefix-decrement-expressions -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: class member operator fun dec return value infers Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Counter(var value: Int) {
    operator fun dec() = Counter(--value)
}

fun case1() {
    checkSubtype<Int>(Counter(1).dec().value)
}
