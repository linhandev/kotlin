// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, elvis-operator-expressions -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 8 -> sentence 8
 *                built-in-types-and-their-semantics, kotlin.nothing -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: Elvis right-hand return with zero default type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(x: String?): Int {
    return x?.length ?: return 0
}

fun case1_check() {
    checkSubtype<Int>(case1("hi"))
}

// TESTCASE NUMBER: 2
fun case2(x: String?): Int {
    return x?.length ?: return 0
}

fun case2_check() {
    checkSubtype<Int>(case2(null))
}
