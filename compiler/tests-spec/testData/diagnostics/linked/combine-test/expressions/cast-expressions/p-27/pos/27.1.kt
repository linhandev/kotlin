// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, cast-expressions -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: reified inline as T infers T
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
inline fun <reified T> cast(x: Any): T = x as T

fun case1() {
    checkSubtype<Int>(cast<Int>(1))
}
