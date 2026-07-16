// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, jump-expressions, throw-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: throw non-exception type String reports TYPE_MISMATCH
 */

// TESTCASE NUMBER: 1
fun case1(): Nothing {
    throw <!TYPE_MISMATCH!>"str"<!>
}
