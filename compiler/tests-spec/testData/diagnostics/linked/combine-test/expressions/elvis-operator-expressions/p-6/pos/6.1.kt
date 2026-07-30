// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, elvis-operator-expressions -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: expressions, jump-expressions, throw-expressions -> paragraph 6 -> sentence 6
 *                built-in-types-and-their-semantics, kotlin.nothing -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: Elvis right-hand throw after nullable conversion infers Int type
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val s: String? = "42"
    checkSubtype<Int>(s?.toIntOrNull() ?: throw NumberFormatException(s))
}

// TESTCASE NUMBER: 2
fun case2() {
    val s: String? = "abc"
    checkSubtype<Int>(s?.toIntOrNull() ?: throw NumberFormatException(s))
}
