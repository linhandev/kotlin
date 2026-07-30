// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 24 -> sentence 24
 *                expressions, elvis-operator-expressions -> paragraph 24 -> sentence 24
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: parenthesized nullable try with Elvis then .length infers Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val x: String? = "hi"
    checkSubtype<Int>(((try {
        if (false) error("boom")
        x
    } catch (e: Exception) {
        null
    }) ?: "fallback").length)
}

// TESTCASE NUMBER: 2
fun case2() {
    val x: String? = null
    checkSubtype<Int>(((try {
        if (false) error("boom")
        x
    } catch (e: Exception) {
        null
    }) ?: "fallback").length)
}
