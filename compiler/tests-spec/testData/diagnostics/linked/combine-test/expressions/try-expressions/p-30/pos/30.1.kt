// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 30 -> sentence 30
 * PRIMARY LINKS: expressions, elvis-operator-expressions -> paragraph 30 -> sentence 30
 * NUMBER: 1
 * DESCRIPTION: Elvis with non-nullable left inside try reports USELESS_ELVIS and type remains Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    checkSubtype<Int>(try {
        1 <!USELESS_ELVIS!>?: 2<!>
    } catch (e: Exception) {
        0
    })
}
