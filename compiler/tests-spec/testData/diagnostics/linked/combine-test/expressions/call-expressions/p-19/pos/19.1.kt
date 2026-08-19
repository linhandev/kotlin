// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 19 -> sentence 19
 *                overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: spread operator expands array as positional vararg arguments
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun f(vararg xs: Int): Int {
    var s = 0
    for (x in xs) s += x
    return s
}

fun case_1() {
    checkSubtype<Int>(f(*intArrayOf(1, 2, 3)))
}
