// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, conditional-expressions -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 24 -> sentence 24
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 24 -> sentence 24
 *                expressions, elvis-operator-expressions -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: conditional expression branch with safe call and Elvis operator type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val flag = true
    val x: String? = "hello"
    checkSubtype<Int>(if (flag) x?.length ?: -1 else 0)
}

// TESTCASE NUMBER: 2
fun case2() {
    val flag = true
    val x: String? = null
    checkSubtype<Int>(if (flag) x?.length ?: -1 else 0)
}

// TESTCASE NUMBER: 3
fun case3() {
    val flag = false
    val x: String? = "hello"
    checkSubtype<Int>(if (flag) x?.length ?: -1 else 0)
}
