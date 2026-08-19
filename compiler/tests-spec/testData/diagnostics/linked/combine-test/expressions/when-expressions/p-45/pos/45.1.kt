// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 45 -> sentence 45
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 45 -> sentence 45
 *                type-inference, smart-casts -> paragraph 45 -> sentence 45
 *                type-inference, introduction-1 -> paragraph 45 -> sentence 45
 * NUMBER: 1
 * DESCRIPTION: when expression with is branches returning different types infers common supertype Any
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val x: Any = "hi"
    checkSubtype<Any>(when (x) {
        is String -> x.length
        is Int -> x.toString()
        else -> false
    })
}

// TESTCASE NUMBER: 2
fun case2() {
    val x: Any = 1
    checkSubtype<Any>(when (x) {
        is String -> x.length
        is Int -> x.toString()
        else -> false
    })
}

// TESTCASE NUMBER: 3
fun case3() {
    val x: Any = 1.5
    checkSubtype<Any>(when (x) {
        is String -> x.length
        is Int -> x.toString()
        else -> false
    })
}
