// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE -DEBUG_INFO_MISSING_UNRESOLVED
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: destructuring cannot replace multiple independent parameters
 */

// TESTCASE NUMBER: 1
fun combine(block: (Int, Int) -> Int): Int = block(1, 2)

fun case_1() =
    combine <!ARGUMENT_TYPE_MISMATCH!>{ <!COMPONENT_FUNCTION_MISSING, COMPONENT_FUNCTION_MISSING!>(a, b)<!> -> <!RETURN_TYPE_MISMATCH!>a + b<!> }<!>
