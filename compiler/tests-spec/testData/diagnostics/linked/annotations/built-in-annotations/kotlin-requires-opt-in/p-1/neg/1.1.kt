// FIR_IDENTICAL
// WITH_STDLIB
// OPT_IN: kotlin.RequiresOptIn
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, built-in-annotations, kotlin-requires-opt-in -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: using experimental feature without OptIn produces OPT_IN_USAGE_ERROR
 */

// TESTCASE NUMBER: 1
@RequiresOptIn(message = "Experimental API", level = RequiresOptIn.Level.ERROR)
annotation class ExperimentalApi17551

@ExperimentalApi17551
fun experimentalFun17551() {}

fun caller17551() {
    <!OPT_IN_USAGE_ERROR!>experimentalFun17551<!>()
}
