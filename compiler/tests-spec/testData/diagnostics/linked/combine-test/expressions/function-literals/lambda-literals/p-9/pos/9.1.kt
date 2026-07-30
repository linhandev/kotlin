// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: single-parameter body destructuring is equivalent to parameter destructuring
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(ps: List<Pair<Int, Int>>) {
    val r = ps.map { it: Pair<Int, Int> ->
        val (a, b) = it
        a + b
    }
    checkSubtype<List<Int>>(r)
}
