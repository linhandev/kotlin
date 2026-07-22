// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, built-in-annotations, kotlin-deprecated -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: Deprecated with replaceWith ReplaceWith expression compiles
 */

// TESTCASE NUMBER: 1
fun newFun17621(): Int = 1

@Deprecated("Use newFun instead", ReplaceWith("newFun()"))
fun oldFun17621(): Int = 2
