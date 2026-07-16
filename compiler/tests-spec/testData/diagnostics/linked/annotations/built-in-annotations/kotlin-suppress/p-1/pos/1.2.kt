// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, built-in-annotations, kotlin-suppress -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Suppress with vararg multiple names suppresses compiler warnings
 */

// TESTCASE NUMBER: 1
@Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
fun suppressed17652(unusedParam17652: Int) {
    val unusedVar17652 = 1
}
