// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: lambda destructuring parameters may have explicit types
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val r = listOf(1 to "a").map { (i: Int, s: String) -> i + s.length }
    checkSubtype<List<Int>>(r)
}
