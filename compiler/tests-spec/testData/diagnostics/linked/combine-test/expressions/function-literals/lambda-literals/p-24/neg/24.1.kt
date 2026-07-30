// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -UNUSED_DESTRUCTURED_PARAMETER_ENTRY
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: IntArray only provides component1..component5 so a sixth lambda destructuring binding fails
 */

// TESTCASE NUMBER: 1
fun case_1(a: IntArray): Int =
    a.let { (x, y, z, u, v, <!COMPONENT_FUNCTION_MISSING!>w<!>) -> x + y }
