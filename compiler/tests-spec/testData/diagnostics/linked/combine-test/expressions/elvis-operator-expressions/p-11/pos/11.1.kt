// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, elvis-operator-expressions -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: expressions, jump-expressions, throw-expressions -> paragraph 11 -> sentence 11
 *                built-in-types-and-their-semantics, kotlin.nothing -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: Elvis after takeIf with throw infers String type
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val s: String? = "hi"
    checkSubtype<String>(s?.takeIf { it.isNotEmpty() } ?: throw IllegalArgumentException("empty"))
}

// TESTCASE NUMBER: 2
fun case2() {
    val s: String? = ""
    checkSubtype<String>(s?.takeIf { it.isNotEmpty() } ?: throw IllegalArgumentException("empty"))
}
