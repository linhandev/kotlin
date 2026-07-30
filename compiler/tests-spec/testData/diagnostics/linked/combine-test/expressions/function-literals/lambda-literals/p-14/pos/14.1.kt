// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: nested destructuring via lambda parameter and body destructuring is allowed
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(p: Pair<Pair<Int, Int>, Int>) {
    val r = p.let { (ab, c) ->
        val (a, b) = ab
        a + b + c
    }
    checkSubtype<Int>(r)
}
