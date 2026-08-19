// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 31 -> sentence 31
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 31 -> sentence 31
 *                expressions, range-expressions -> paragraph 31 -> sentence 31
 *                type-system, introduction-1 -> paragraph 31 -> sentence 31
 *                type-inference, smart-casts -> paragraph 31 -> sentence 31
 *                expressions, conditional-expressions -> paragraph 31 -> sentence 31
 * NUMBER: 1
 * DESCRIPTION: when expression with in range branch after nullable subject is narrowed by if condition type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val x: Int? = 5
    checkSubtype<String>(if (x != null) when (x) {
        in 1..10 -> "inside"
        else -> "other"
    } else "null")
}

// TESTCASE NUMBER: 2
fun case2() {
    val x: Int? = null
    checkSubtype<String>(if (x != null) when (x) {
        in 1..10 -> "inside"
        else -> "other"
    } else "null")
}

// TESTCASE NUMBER: 3
fun case3() {
    val x: Int? = 11
    checkSubtype<String>(if (x != null) when (x) {
        in 1..10 -> "inside"
        else -> "other"
    } else "null")
}
