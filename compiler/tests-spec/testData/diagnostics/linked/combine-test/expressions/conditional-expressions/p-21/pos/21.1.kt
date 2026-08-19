// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, conditional-expressions -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 20 -> sentence 20
 *                expressions, elvis-operator-expressions -> paragraph 20 -> sentence 20
 *                expressions, jump-expressions, return-expressions -> paragraph 20 -> sentence 20
 *                built-in-types-and-their-semantics, kotlin.nothing -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: conditional expression branch with Elvis operator and return expression type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(flag: Boolean, x: String?): Int {
    return if (flag) (x ?: return -1).length else 0
}

fun wrapper1() {
    checkSubtype<Int>(case1(true, "hello"))
}

// TESTCASE NUMBER: 2
fun wrapper2() {
    checkSubtype<Int>(case1(false, null))
}
