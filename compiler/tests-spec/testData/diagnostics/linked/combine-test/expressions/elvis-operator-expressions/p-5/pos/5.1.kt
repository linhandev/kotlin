// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, elvis-operator-expressions -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 5 -> sentence 5
 *                built-in-types-and-their-semantics, kotlin.nothing -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: Elvis right-hand return in local variable initializer type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(): Int {
    val x: String? = "hi"
    val n = x?.length ?: return -1
    checkSubtype<Int>(n)
    return n
}

// TESTCASE NUMBER: 2
fun case2(): Int {
    val x: String? = null
    val n = x?.length ?: return -1
    checkSubtype<Int>(n)
    return n
}
