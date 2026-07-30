// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE -DEBUG_INFO_MISSING_UNRESOLVED
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 59 -> sentence 59
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 59 -> sentence 59
 * NUMBER: 1
 * DESCRIPTION: return@missing reports unresolved label
 */

// TESTCASE NUMBER: 1
fun case_1() {
    listOf(1).forEach { return<!UNRESOLVED_LABEL!>@missing<!> }
}
