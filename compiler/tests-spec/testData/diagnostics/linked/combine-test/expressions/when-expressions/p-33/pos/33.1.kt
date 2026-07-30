// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 33 -> sentence 33
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 33 -> sentence 33
 *                expressions, range-expressions -> paragraph 33 -> sentence 33
 *                type-inference, introduction-1 -> paragraph 33 -> sentence 33
 * NUMBER: 1
 * DESCRIPTION: when expression with Int/String branches infers common Comparable<*>
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val x = 5
    checkSubtype<Comparable<*>>(when (x) {
        in 1..10 -> 1
        else -> "other"
    })
    checkSubtype<Boolean>(x in 1..10)
}

// TESTCASE NUMBER: 2
fun case2() {
    val x = 0
    checkSubtype<Comparable<*>>(when (x) {
        in 1..10 -> 1
        else -> "other"
    })
    checkSubtype<Boolean>(x !in 1..10)
}

// TESTCASE NUMBER: 3
fun case3() {
    val x = 11
    checkSubtype<Comparable<*>>(when (x) {
        in 1..10 -> 1
        else -> "other"
    })
    checkSubtype<Boolean>(x !in 1..10)
}
