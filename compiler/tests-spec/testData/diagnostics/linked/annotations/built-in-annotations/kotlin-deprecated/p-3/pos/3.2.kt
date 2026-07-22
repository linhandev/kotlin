// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -DEPRECATION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, built-in-annotations, kotlin-deprecated -> paragraph 3 -> sentence 3
 * NUMBER: 2
 * DESCRIPTION: Deprecated with HIDDEN level compiles without deprecation warning on declaration
 */

// TESTCASE NUMBER: 1
@Deprecated("Use new instead", level = DeprecationLevel.HIDDEN)
fun hiddenDecl17632() {}
