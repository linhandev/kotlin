// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, conditional-expressions -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 17 -> sentence 17
 *                expressions, elvis-operator-expressions -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: conditional expression condition with Elvis operator converting nullable Boolean to Boolean type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val x: Boolean? = null
    checkSubtype<Int>(if (x ?: false) 1 else 0)
}

// TESTCASE NUMBER: 2
fun case2() {
    val x: Boolean? = true
    checkSubtype<Int>(if (x ?: false) 1 else 0)
}
