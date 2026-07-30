// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, elvis-operator-expressions -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: Elvis with non-nullable literal left and return RHS reports USELESS_ELVIS; type remains Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(): Int {
    return 1 <!USELESS_ELVIS!>?: return 0<!>
}

fun case1_check() {
    checkSubtype<Int>(case1())
}
