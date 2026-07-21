// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, declaration-visibility -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: private declarations are accessible from the same scope they are declared in
 */

// TESTCASE NUMBER: 1
private fun hiddenTopLevel(): Int = 1

fun useHiddenInSameFile(): Int = hiddenTopLevel()

// TESTCASE NUMBER: 2
class Container {
    private val value = 2
    fun read(): Int = value
}
