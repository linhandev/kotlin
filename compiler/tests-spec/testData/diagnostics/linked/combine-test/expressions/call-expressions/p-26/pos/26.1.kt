// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -NOTHING_TO_INLINE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 26 -> sentence 26
 *                overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: inline function default parameter works like non-inline function
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
inline fun g(x: Int = 1): Int = x

fun case_1() {
    checkSubtype<Int>(g())
}
