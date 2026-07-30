// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 22 -> sentence 22
 *                expressions, range-expressions -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: when expression with multiple range branches matched in order type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val x = 5
    checkSubtype<String>(when (x) {
        in 1..10 -> "small"
        in 11..100 -> "medium"
        else -> "large"
    })
}

// TESTCASE NUMBER: 2
fun case2() {
    val x = 50
    checkSubtype<String>(when (x) {
        in 1..10 -> "small"
        in 11..100 -> "medium"
        else -> "large"
    })
}

// TESTCASE NUMBER: 3
fun case3() {
    val x = 101
    checkSubtype<String>(when (x) {
        in 1..10 -> "small"
        in 11..100 -> "medium"
        else -> "large"
    })
}
