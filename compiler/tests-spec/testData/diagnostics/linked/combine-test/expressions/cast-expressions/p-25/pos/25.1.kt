// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, cast-expressions -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: filterIsInstance infers List of String
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val xs: List<Any> = listOf("a", 1)
    checkSubtype<List<String>>(xs.filterIsInstance<String>())
}
