// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -UNUSED_DESTRUCTURED_PARAMETER_ENTRY
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: more destructuring bindings than componentN fails
 */

// TESTCASE NUMBER: 1
fun case_1(p: Pair<Int, Int>): Int =
    p.let { (a, b, <!COMPONENT_FUNCTION_MISSING!>c<!>) -> a }
