// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -NOTHING_TO_INLINE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: inline functional parameters cannot be stored or leak from the inline function
 */

// TESTCASE NUMBER: 1
inline fun storeParameter(block: () -> Unit) {
    val saved = <!USAGE_IS_NOT_INLINABLE!>block<!>
    saved()
}
