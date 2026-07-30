// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE -DEBUG_INFO_MISSING_UNRESOLVED
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 43 -> sentence 43
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 43 -> sentence 43
 * NUMBER: 1
 * DESCRIPTION: crossinline parameter in both forbids non-local return
 */

// TESTCASE NUMBER: 1
inline fun both(noinline a: () -> Unit, crossinline b: () -> Unit) {
    a()
    b()
}

fun case_1() {
    both({}, { <!RETURN_NOT_ALLOWED!>return<!> })
}
