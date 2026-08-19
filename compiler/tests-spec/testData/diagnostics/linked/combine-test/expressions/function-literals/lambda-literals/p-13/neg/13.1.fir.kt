// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_MISSING_UNRESOLVED
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: destructuring binding types must match componentN return types
 */

// TESTCASE NUMBER: 1
fun case_1(p: Pair<Int, String>): Int =
    p.let { (<!COMPONENT_FUNCTION_RETURN_TYPE_MISMATCH!>a: String<!>, <!COMPONENT_FUNCTION_RETURN_TYPE_MISMATCH!>b: Int<!>) -> a.length + b }
