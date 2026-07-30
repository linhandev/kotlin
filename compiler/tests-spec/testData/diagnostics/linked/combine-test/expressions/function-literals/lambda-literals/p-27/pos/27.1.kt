// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: fold with non-destructured accumulator and destructured Pair
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(ps: List<Pair<Int, Int>>) {
    val r = ps.fold(0) { sum, (x, y) -> sum + x * y }
    checkSubtype<Int>(r)
}
