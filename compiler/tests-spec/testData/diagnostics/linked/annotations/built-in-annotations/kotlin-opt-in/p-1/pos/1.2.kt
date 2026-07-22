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
 * NUMBER: 2
 * DESCRIPTION: OptIn with vararg multiple marker classes allows experimental feature usage
 */

// TESTCASE NUMBER: 1
@RequiresOptIn(message = "Experimental A", level = RequiresOptIn.Level.ERROR)
annotation class MarkerA17562

@RequiresOptIn(message = "Experimental B", level = RequiresOptIn.Level.ERROR)
annotation class MarkerB17562

@MarkerA17562
fun featureA17562() {}

@MarkerB17562
fun featureB17562() {}

@OptIn(MarkerA17562::class, MarkerB17562::class)
fun caller17562() {
    featureA17562()
    featureB17562()
}
