// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, elvis-operator-expressions -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: expressions, jump-expressions, throw-expressions -> paragraph 10 -> sentence 10
 *                expressions, jump-expressions, return-expressions -> paragraph 10 -> sentence 10
 *                built-in-types-and-their-semantics, kotlin.nothing -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: Elvis with literal default and with throw both infer Int for nullable length
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val x: String? = "hi"
    checkSubtype<Int>(x?.length ?: -1)
}

// TESTCASE NUMBER: 2
fun case2() {
    val x: String? = null
    checkSubtype<Int>(x?.length ?: -1)
}

// TESTCASE NUMBER: 3
fun case3() {
    val x: String? = "hi"
    checkSubtype<Int>(x?.length ?: throw Exception())
}

// TESTCASE NUMBER: 4
fun case4() {
    val x: String? = null
    checkSubtype<Int>(x?.length ?: throw Exception())
}
