// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, elvis-operator-expressions -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 7 -> sentence 7
 *                built-in-types-and-their-semantics, kotlin.nothing -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: nested Elvis returns inside one return unify Int early-exit across two nullables
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(x: String?, y: String?): Int {
    return x?.length ?: return (y?.length ?: return -1)
}

fun case_1_check() {
    checkSubtype<Int>(case_1("hi", null))
    checkSubtype<Int>(case_1(null, "ab"))
    checkSubtype<Int>(case_1(null, null))
}
