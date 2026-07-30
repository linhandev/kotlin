// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE -DEBUG_INFO_MISSING_UNRESOLVED
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: private component1 is invisible for lambda destructuring
 */

// TESTCASE NUMBER: 1
data class Secret(private val x: Int, val y: Int)

fun case_1(s: Secret): Int =
    s.let { (<!INVISIBLE_REFERENCE!>a<!>, b) -> a + b }
