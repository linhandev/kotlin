// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 26 -> sentence 26
 *                expressions, range-expressions -> paragraph 26 -> sentence 26
 *                expressions, when-expressions -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: when expression with constant equality branch and range containment branch type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val x = 0
    checkSubtype<String>(when (x) {
        0 -> "zero"
        in 1..10 -> "small"
        else -> "other"
    })
}

// TESTCASE NUMBER: 2
fun case2() {
    val x = 5
    checkSubtype<String>(when (x) {
        0 -> "zero"
        in 1..10 -> "small"
        else -> "other"
    })
}

// TESTCASE NUMBER: 3
fun case3() {
    val x = 11
    checkSubtype<String>(when (x) {
        0 -> "zero"
        in 1..10 -> "small"
        else -> "other"
    })
}
