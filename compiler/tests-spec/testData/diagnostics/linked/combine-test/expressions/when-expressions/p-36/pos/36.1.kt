// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 36 -> sentence 36
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 36 -> sentence 36
 *                type-inference, smart-casts -> paragraph 36 -> sentence 36
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 36 -> sentence 36
 * NUMBER: 1
 * DESCRIPTION: when expression with is branch smart cast for property access type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val x: Any = "hello"
    checkSubtype<Int>(when (x) {
        is String -> x.length
        else -> -1
    })
}

// TESTCASE NUMBER: 2
fun case2() {
    val x: Any = 123
    checkSubtype<Int>(when (x) {
        is String -> x.length
        else -> -1
    })
}
