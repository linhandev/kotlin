// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 10 -> sentence 10
 *                overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: omitted-default calls f() + f() infer Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
var n = 0

fun def(): Int = ++n

fun f(x: Int = def()): Int = x

fun case_1() {
    checkSubtype<Int>(f() + f())
}
