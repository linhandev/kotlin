// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -NOTHING_TO_INLINE -DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE -DEBUG_INFO_MISSING_UNRESOLVED
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 42 -> sentence 42
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 42 -> sentence 42
 * NUMBER: 1
 * DESCRIPTION: noinline lambda parameter forbids non-local return
 */

// TESTCASE NUMBER: 1
inline fun callInline(noinline block: () -> Unit): Unit = block()

fun case_1() {
    callInline { <!RETURN_NOT_ALLOWED!>return<!> }
}
