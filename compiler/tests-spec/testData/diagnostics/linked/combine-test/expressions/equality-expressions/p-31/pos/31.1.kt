// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 31 -> sentence 31
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 31 -> sentence 31
 * NUMBER: 1
 * DESCRIPTION: default Int? and List element == 1 infer Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    fun withDefault(n: Int? = 1): Boolean = n == 1
    checkSubtype<Boolean>(withDefault())
    checkSubtype<Boolean>(listOf<Int?>(1).first() == 1)
}
