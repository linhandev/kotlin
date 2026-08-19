// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, elvis-operator-expressions -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: Elvis with non-nullable left and return RHS reports USELESS_ELVIS; type remains Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(x: String): Int {
    return x.length <!USELESS_ELVIS!>?: return -1<!>
}

fun case1_check() {
    checkSubtype<Int>(case1("hi"))
}
