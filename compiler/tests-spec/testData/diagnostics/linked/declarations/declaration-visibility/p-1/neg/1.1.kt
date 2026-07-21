// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, declaration-visibility -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: visibility modifiers restrict access from incompatible scopes
 */

// TESTCASE NUMBER: 1
class Holder {
    private fun secret(): Int = 1
}

fun illegalAccess(): Int = Holder().<!INVISIBLE_MEMBER!>secret<!>()
