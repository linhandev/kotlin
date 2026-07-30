// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 17 -> sentence 17
 *                expressions, elvis-operator-expressions -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: try expression result with Elvis providing default value type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val x: String? = "hi"
    checkSubtype<String>(try {
        x
    } catch (e: Exception) {
        null
    } ?: "fallback")
}

// TESTCASE NUMBER: 2
fun case2() {
    val x: String? = null
    checkSubtype<String>(try {
        x
    } catch (e: Exception) {
        null
    } ?: "fallback")
}
