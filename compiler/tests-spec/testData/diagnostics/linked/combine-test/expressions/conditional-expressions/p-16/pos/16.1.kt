// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, conditional-expressions -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 15 -> sentence 15
 *                expressions, elvis-operator-expressions -> paragraph 15 -> sentence 15
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: conditional expression true branch with Elvis operator providing non-null receiver type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val flag = true
    val x: String? = "hello"
    checkSubtype<Int>(if (flag) (x ?: "").length else 0)
}

// TESTCASE NUMBER: 2
fun case2() {
    val flag = true
    val x: String? = null
    checkSubtype<Int>(if (flag) (x ?: "").length else 0)
}

// TESTCASE NUMBER: 3
fun case3() {
    val flag = false
    val x: String? = "hello"
    checkSubtype<Int>(if (flag) (x ?: "").length else 0)
}
