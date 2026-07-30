// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 32 -> sentence 32
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 32 -> sentence 32
 *                expressions, range-expressions -> paragraph 32 -> sentence 32
 *                type-system, introduction-1 -> paragraph 32 -> sentence 32
 *                expressions, elvis-operator-expressions -> paragraph 32 -> sentence 32
 * NUMBER: 1
 * DESCRIPTION: when expression with Elvis operator on nullable subject before range containment branch type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val x: Int? = 5
    checkSubtype<String>(when (x ?: -1) {
        in 1..10 -> "inside"
        else -> "other"
    })
}

// TESTCASE NUMBER: 2
fun case2() {
    val x: Int? = null
    checkSubtype<String>(when (x ?: -1) {
        in 1..10 -> "inside"
        else -> "other"
    })
}

// TESTCASE NUMBER: 3
fun case3() {
    val x: Int? = 11
    checkSubtype<String>(when (x ?: -1) {
        in 1..10 -> "inside"
        else -> "other"
    })
}
