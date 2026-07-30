// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 40 -> sentence 40
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 40 -> sentence 40
 *                type-system, introduction-1 -> paragraph 40 -> sentence 40
 *                type-inference, smart-casts -> paragraph 40 -> sentence 40
 * NUMBER: 1
 * DESCRIPTION: when expression with nullable subject is branch smart cast to non-null target type type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val x: Any? = "hi"
    checkSubtype<Int>(when (x) {
        is String -> x.length
        null -> 0
        else -> -1
    })
}

// TESTCASE NUMBER: 2
fun case2() {
    val x: Any? = null
    checkSubtype<Int>(when (x) {
        is String -> x.length
        null -> 0
        else -> -1
    })
}

// TESTCASE NUMBER: 3
fun case3() {
    val x: Any? = 123
    checkSubtype<Int>(when (x) {
        is String -> x.length
        null -> 0
        else -> -1
    })
}
