// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, object-declaration -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: object may implement interfaces and override members
 */

// TESTCASE NUMBER: 1
interface Named { val name: String }

object App : Named {
    override val name: String = "app"
}
