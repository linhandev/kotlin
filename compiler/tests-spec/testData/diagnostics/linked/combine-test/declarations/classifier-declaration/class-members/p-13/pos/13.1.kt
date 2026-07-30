// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 13 -> sentence 13
 *                expressions, prefix-expressions, prefix-increment-expressions -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: class member operator fun inc return value infers Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Counter(var value: Int) {
    operator fun inc() = Counter(++value)
}

fun case1() {
    checkSubtype<Int>(Counter(0).inc().value)
}
