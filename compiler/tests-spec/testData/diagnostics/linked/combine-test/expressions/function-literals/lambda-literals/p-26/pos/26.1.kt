// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: fold second parameter can be Pair-destructured
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(ps: List<Pair<Int, Int>>) {
    val r = ps.fold(0) { acc, (a, b) -> acc + a + b }
    checkSubtype<Int>(r)
}
