// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, elvis-operator-expressions -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: expressions, jump-expressions, throw-expressions -> paragraph 1 -> sentence 1
 *                built-in-types-and-their-semantics, kotlin.nothing -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Elvis right-hand throw is Nothing and Elvis expression type is Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val x: String? = "hi"
    checkSubtype<Int>(x?.length ?: throw IllegalArgumentException())
}

// TESTCASE NUMBER: 2
fun case2() {
    val x: String? = null
    checkSubtype<Int>(x?.length ?: throw IllegalArgumentException())
}
