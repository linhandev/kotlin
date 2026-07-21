// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, declaration-visibility -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: protected members cannot be accessed from unrelated scopes
 */

// TESTCASE NUMBER: 1
open class Base {
    protected fun guarded(): Int = 1
}

fun accessProtectedFromOutside(base: Base): Int = base.<!INVISIBLE_MEMBER!>guarded<!>()
