// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 23 -> sentence 23
 *                expressions, range-expressions -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: when expression branch with !in range containment condition type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val x = 0
    checkSubtype<String>(when (x) {
        !in 1..10 -> "outside"
        else -> "inside"
    })
}

// TESTCASE NUMBER: 2
fun case2() {
    val x = 5
    checkSubtype<String>(when (x) {
        !in 1..10 -> "outside"
        else -> "inside"
    })
}
