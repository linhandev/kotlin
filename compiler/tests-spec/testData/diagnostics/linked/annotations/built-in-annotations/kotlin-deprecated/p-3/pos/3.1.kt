// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, built-in-annotations, kotlin-deprecated -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: Deprecated with WARNING level produces compile-time warning on usage
 */

// TESTCASE NUMBER: 1
@Deprecated("Use newFun instead", level = DeprecationLevel.WARNING)
fun warningFun17631() {}

fun useWarning17631() {
    <!DEPRECATION!>warningFun17631<!>()
}
