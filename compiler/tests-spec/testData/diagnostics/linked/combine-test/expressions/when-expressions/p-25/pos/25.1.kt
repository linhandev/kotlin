// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 25 -> sentence 25
 *                expressions, range-expressions -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: when expression branch with half-open range containment condition type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val x = 9
    checkSubtype<String>(when (x) {
        in 1..<10 -> "one-digit"
        else -> "other"
    })
}

// TESTCASE NUMBER: 2
fun case2() {
    val x = 10
    checkSubtype<String>(when (x) {
        in 1..<10 -> "one-digit"
        else -> "other"
    })
}
