// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, elvis-operator-expressions -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: expressions, jump-expressions, throw-expressions -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: Elvis with non-nullable left and throw RHS reports USELESS_ELVIS; type remains Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(x: String) {
    checkSubtype<Int>(x.length <!USELESS_ELVIS!>?: throw IllegalArgumentException()<!>)
}
