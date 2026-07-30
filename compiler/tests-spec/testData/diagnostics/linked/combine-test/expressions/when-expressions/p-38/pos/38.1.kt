// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 38 -> sentence 38
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 38 -> sentence 38
 *                type-inference, smart-casts -> paragraph 38 -> sentence 38
 * NUMBER: 1
 * DESCRIPTION: when expression with !is branch excluding type and else branch smart cast type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val x: Any = 123
    checkSubtype<String>(when (x) {
        !is String -> "not string"
        else -> x.uppercase()
    })
}

// TESTCASE NUMBER: 2
fun case2() {
    val x: Any = "hi"
    checkSubtype<String>(when (x) {
        !is String -> "not string"
        else -> x.uppercase()
    })
}
