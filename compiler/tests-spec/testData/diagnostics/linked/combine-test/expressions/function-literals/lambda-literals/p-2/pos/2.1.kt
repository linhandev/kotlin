// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 2 -> sentence 2
 *                type-inference, function-signature-type-inference, statements-with-lambda-literals -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: lambda destructuring parameter types inferred from Pair argument
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val r = (1 to "a").let { (i, s) -> i + s.length }
    checkSubtype<Int>(r)
}
