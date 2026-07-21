// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, declaration-visibility -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: private class members cannot be accessed from outside the declaring class
 */

// TESTCASE NUMBER: 1
class Vault {
    private fun secret(): Int = 1
}

fun accessClassPrivate(): Int = Vault().<!INVISIBLE_REFERENCE!>secret<!>()
