// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -UNREACHABLE_CODE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 62 -> sentence 62
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 62 -> sentence 62
 *                type-inference, introduction-1 -> paragraph 62 -> sentence 62
 *                built-in-types-and-their-semantics, kotlin.nothing -> paragraph 62 -> sentence 62
 * NUMBER: 1
 * DESCRIPTION: Nothing (TODO()) participates in choose type inference yielding Int, verifying type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun <T> choose(a: T, b: T): T = a

fun case_1() {
    checkSubtype<Int>(choose(TODO(), 1))
}
