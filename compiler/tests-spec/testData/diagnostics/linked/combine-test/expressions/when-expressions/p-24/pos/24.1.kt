// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 24 -> sentence 24
 *                expressions, range-expressions -> paragraph 24 -> sentence 24
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: when expression branch with range bounds from variable expressions type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val x = 5
    val start = 1
    val end = 10
    checkSubtype<Boolean>(when (x) {
        in start..end -> true
        else -> false
    })
}

// TESTCASE NUMBER: 2
fun case2() {
    val x = 0
    val start = 1
    val end = 10
    checkSubtype<Boolean>(when (x) {
        in start..end -> true
        else -> false
    })
}
