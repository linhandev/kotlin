// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: zip result pairs can be destructured in a lambda
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(a: List<Int>, b: List<Int>) {
    val r = a.zip(b).map { (x, y) -> x + y }
    checkSubtype<List<Int>>(r)
}
