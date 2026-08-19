// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: Triple destructures into three bindings in a lambda
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(t: Triple<Int, Int, Int>) {
    val r = t.let { (a, b, c) -> a + b + c }
    checkSubtype<Int>(r)
}
