// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, built-in-annotations, kotlin-deprecated -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: Deprecated with ERROR level produces compile-time error on usage
 */

// TESTCASE NUMBER: 1
@Deprecated("Use newFun instead", level = DeprecationLevel.ERROR)
fun errorFun17641() {}

fun useError17641() {
    <!DEPRECATION_ERROR!>errorFun17641<!>()
}
