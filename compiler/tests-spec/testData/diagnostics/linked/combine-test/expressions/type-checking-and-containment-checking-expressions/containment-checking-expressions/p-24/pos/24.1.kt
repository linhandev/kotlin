// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 24 -> sentence 24
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: in operator on Map checks key membership via containsKey convention and infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val m = mapOf("a" to 1)
    checkSubtype<Boolean>("a" in m)
    checkSubtype<Boolean>("a" in m == m.containsKey("a"))
}
