// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE -DEBUG_INFO_MISSING_UNRESOLVED
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: non-data type without componentN cannot be lambda-destructured
 */

// TESTCASE NUMBER: 1
class NotData(val x: Int, val y: Int)

fun case_1(xs: List<NotData>): Int =
    xs.<!NONE_APPLICABLE!>sumOf<!> { (<!COMPONENT_FUNCTION_MISSING!>x<!>, <!COMPONENT_FUNCTION_MISSING!>y<!>) -> <!DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE!>x<!> <!DEBUG_INFO_MISSING_UNRESOLVED!>+<!> <!DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE!>y<!> }
