// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, conditional-expressions -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 16 -> sentence 16
 *                expressions, elvis-operator-expressions -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: conditional expression else branch with Elvis operator providing default value type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val flag = false
    val x: String? = null
    checkSubtype<String>(if (flag) "ok" else x ?: "fallback")
}

// TESTCASE NUMBER: 2
fun case2() {
    val flag = true
    val x: String? = null
    checkSubtype<String>(if (flag) "ok" else x ?: "fallback")
}
