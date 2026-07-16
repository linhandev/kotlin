// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: scopes-and-identifiers, identifiers-and-paths -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: return answer reads local val answer = 42
 */

// TESTCASE NUMBER: 1
fun case1(): Int {
    val answer = 42
    return answer
}
