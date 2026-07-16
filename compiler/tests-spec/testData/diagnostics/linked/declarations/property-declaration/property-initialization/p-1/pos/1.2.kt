// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, property-initialization -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: member property initialized in init block
 */

// TESTCASE NUMBER: 1
class Holder {
    val value: Int
    init {
        value = 42
    }
}
