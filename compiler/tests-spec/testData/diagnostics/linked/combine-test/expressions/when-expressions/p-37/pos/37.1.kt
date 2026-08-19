// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 37 -> sentence 37
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 37 -> sentence 37
 *                type-inference, smart-casts -> paragraph 37 -> sentence 37
 *                type-inference, introduction-1 -> paragraph 37 -> sentence 37
 * NUMBER: 1
 * DESCRIPTION: when expression with multiple is branches smart cast to different types type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val x: Any = "hello"
    checkSubtype<Int>(when (x) {
        is String -> x.length
        is Int -> x + 1
        else -> -1
    })
}

// TESTCASE NUMBER: 2
fun case2() {
    val x: Any = 1
    checkSubtype<Int>(when (x) {
        is String -> x.length
        is Int -> x + 1
        else -> -1
    })
}

// TESTCASE NUMBER: 3
fun case3() {
    val x: Any = 1.5
    checkSubtype<Int>(when (x) {
        is String -> x.length
        is Int -> x + 1
        else -> -1
    })
}
