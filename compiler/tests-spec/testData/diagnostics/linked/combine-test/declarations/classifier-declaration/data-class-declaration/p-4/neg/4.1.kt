// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: non-data class without componentN cannot be destructured
 */

// TESTCASE NUMBER: 1
class NotPt(val x: Int, val y: Int)

fun case_1(p: NotPt): Int {
    val (a, b) = <!COMPONENT_FUNCTION_MISSING, COMPONENT_FUNCTION_MISSING!>p<!>
    return <!DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE!>a<!> <!DEBUG_INFO_MISSING_UNRESOLVED!>+<!> <!DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE!>b<!>
}
