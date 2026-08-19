// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 39 -> sentence 39
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 39 -> sentence 39
 *                type-inference, smart-casts -> paragraph 39 -> sentence 39
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 39 -> sentence 39
 * NUMBER: 1
 * DESCRIPTION: when is branch smart cast then parameterized member calls infer Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val x: Any = "hello"
    checkSubtype<Boolean>(when (x) {
        is String -> x.startsWith("h") && x.endsWith("o")
        else -> false
    })
}

// TESTCASE NUMBER: 2
fun case2() {
    val x: Any = 123
    checkSubtype<Boolean>(when (x) {
        is String -> x.startsWith("h") && x.endsWith("o")
        else -> false
    })
}
