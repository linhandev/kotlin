// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, conditional-expressions -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 21 -> sentence 21
 *                expressions, elvis-operator-expressions -> paragraph 21 -> sentence 21
 *                expressions, jump-expressions, throw-expressions -> paragraph 21 -> sentence 21
 *                built-in-types-and-their-semantics, kotlin.nothing -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: conditional expression branch with Elvis operator and throw expression type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val flag = true
    val x: String? = "hello"
    checkSubtype<Int>(if (flag) (x ?: throw IllegalArgumentException()).length else 0)
}

// TESTCASE NUMBER: 2
fun case2() {
    val flag = false
    val x: String? = null
    checkSubtype<Int>(if (flag) (x ?: throw IllegalArgumentException()).length else 0)
}
