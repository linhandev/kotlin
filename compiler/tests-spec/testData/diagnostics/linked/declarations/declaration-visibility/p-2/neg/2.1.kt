// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, declaration-visibility -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: private top-level declarations cannot be accessed from other files
 */

// FILE: scopeA.kt
private fun hiddenInFileA(): Int = 1

// FILE: scopeB.kt
// TESTCASE NUMBER: 1
fun accessFromOtherFile(): Int = <!INVISIBLE_MEMBER!>hiddenInFileA<!>()
