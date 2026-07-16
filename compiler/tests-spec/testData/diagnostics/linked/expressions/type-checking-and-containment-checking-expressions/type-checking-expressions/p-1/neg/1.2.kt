// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: is check against MutableList<String> on Any reports CANNOT_CHECK_FOR_ERASED
 */

// TESTCASE NUMBER: 1
fun case1(list: Any) {
    val x = list is <!CANNOT_CHECK_FOR_ERASED!>MutableList<String><!>
}
