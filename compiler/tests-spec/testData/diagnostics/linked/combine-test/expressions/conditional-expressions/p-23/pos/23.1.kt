// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, conditional-expressions -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 22 -> sentence 22
 *                type-inference, local-type-inference -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: conditional expression with nullable and non-nullable branches infers nullable result type
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val flag = true
    val x: String? = null
    checkSubtype<String?>(if (flag) x else "default")
}

// TESTCASE NUMBER: 2
fun case2() {
    val flag = false
    val x: String? = "hello"
    checkSubtype<String?>(if (flag) x else "default")
}
