// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, elvis-operator-expressions -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 2 -> sentence 2
 *                built-in-types-and-their-semantics, kotlin.nothing -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: Elvis right-hand return early-exits via block return of Elvis; type is Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(x: String?): Int {
    return x?.length ?: return -1
}

fun case_1_check() {
    checkSubtype<Int>(case_1("hi"))
    checkSubtype<Int>(case_1(null))
}
