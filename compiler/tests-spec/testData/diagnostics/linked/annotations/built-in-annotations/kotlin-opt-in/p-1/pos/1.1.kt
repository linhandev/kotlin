// FIR_IDENTICAL
// WITH_STDLIB
// OPT_IN: kotlin.RequiresOptIn
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, built-in-annotations, kotlin-opt-in -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: OptIn annotation allows usage of RequiresOptIn-marked experimental feature
 */

// FILE: marker17561.kt

// TESTCASE NUMBER: 1
@RequiresOptIn(message = "Experimental API", level = RequiresOptIn.Level.ERROR)
annotation class ExperimentalApi17561

@ExperimentalApi17561
fun experimentalFun17561() {}

// FILE: usage17561.kt

@OptIn(ExperimentalApi17561::class)
fun caller17561() {
    experimentalFun17561()
}
