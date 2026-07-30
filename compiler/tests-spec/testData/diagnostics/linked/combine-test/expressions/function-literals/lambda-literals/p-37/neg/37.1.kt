// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE -DEBUG_INFO_MISSING_UNRESOLVED
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 37 -> sentence 37
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 37 -> sentence 37
 * NUMBER: 1
 * DESCRIPTION: return@map emptyList type mismatches Int map transform
 */

// TESTCASE NUMBER: 1
fun case_1(xs: List<Int>): List<Int> =
    xs.map { <!TYPE_MISMATCH!>if (it < 0) return@map <!TYPE_MISMATCH!>emptyList<Int>()<!> else it * 2<!> }
