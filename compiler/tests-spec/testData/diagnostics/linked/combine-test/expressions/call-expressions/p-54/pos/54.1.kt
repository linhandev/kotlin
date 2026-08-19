// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 54 -> sentence 54
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 54 -> sentence 54
 *                declarations, declaration-site-variance-and-use-site-variance -> paragraph 54 -> sentence 54
 * NUMBER: 1
 * DESCRIPTION: star-projected type as receiver in call to non-generic member
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val xs: List<*> = listOf(1, 2, 3)
    checkSubtype<Int>(xs.size)
}
