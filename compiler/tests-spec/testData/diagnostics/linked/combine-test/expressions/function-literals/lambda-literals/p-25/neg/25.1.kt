// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE -DEBUG_INFO_MISSING_UNRESOLVED
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: List element Int cannot be lambda-destructured as two components
 */

// TESTCASE NUMBER: 1
fun case_1(xs: List<Int>): Int =
    xs.<!NONE_APPLICABLE!>sumOf<!> { (<!COMPONENT_FUNCTION_MISSING!>a<!>, <!COMPONENT_FUNCTION_MISSING!>b<!>) -> <!DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE!>a<!> <!DEBUG_INFO_MISSING_UNRESOLVED!>+<!> <!DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE!>b<!> }
