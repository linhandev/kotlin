// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, elvis-operator-expressions -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: expressions, jump-expressions, throw-expressions -> paragraph 9 -> sentence 9
 *                built-in-types-and-their-semantics, kotlin.nothing -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: Elvis on nullable String with throw on right-hand side infers String type
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val x: String? = "hi"
    checkSubtype<String>(x ?: throw IllegalStateException())
}

// TESTCASE NUMBER: 2
fun case2() {
    val x: String? = null
    checkSubtype<String>(x ?: throw IllegalStateException())
}
