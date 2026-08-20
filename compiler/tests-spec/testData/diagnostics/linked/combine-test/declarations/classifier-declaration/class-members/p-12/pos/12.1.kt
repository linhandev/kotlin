// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 12 -> sentence 12
 *                expressions, prefix-expressions, logical-not-expressions -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: class member operator fun not in logical not expression infers Flag
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Flag(val value: Boolean) {
    operator fun not() = Flag(!value)
}

fun case1() {
    checkSubtype<Flag>(!Flag(true))
}
