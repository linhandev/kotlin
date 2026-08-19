// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: Map.Entry can be destructured in a lambda
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(m: Map<String, Int>) {
    val r = m.entries.sumOf { (k, v) -> k.length + v }
    checkSubtype<Int>(r)
}
