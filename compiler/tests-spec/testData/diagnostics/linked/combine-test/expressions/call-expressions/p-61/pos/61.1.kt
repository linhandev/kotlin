// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 61 -> sentence 61
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 61 -> sentence 61
 *                type-inference, introduction-1 -> paragraph 61 -> sentence 61
 *                overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 61 -> sentence 61
 * NUMBER: 1
 * DESCRIPTION: explicit type argument and named value argument can coexist in a call
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun <T> id(x: T): T = x

fun case_1() {
    checkSubtype<Any>(id<Any>(x = 1))
}
