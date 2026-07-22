// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, built-in-annotations, kotlin-suppress -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Suppress annotation with names suppresses compiler warning on parameter
 */

// TESTCASE NUMBER: 1
fun suppressedParam17651(@Suppress("UNUSED_PARAMETER") unused: Int) {}
