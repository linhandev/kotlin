// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, local-property-declaration -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: local delegated property in function
 */

// TESTCASE NUMBER: 1
fun useLocalDelegate(): String {
    val local by lazy { "local" }
    return local
}
