// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, receivers -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: local extension callable has no dispatch receiver for qualified this@Type
 */

// TESTCASE NUMBER: 1
class Host1104

fun case_1() {
    fun String.localExt1104() {
        val x = this<!UNRESOLVED_REFERENCE!>@Host1104<!>
    }
}
