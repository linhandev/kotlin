// FIR_IDENTICAL
// WITH_STDLIB
// OPT_IN: kotlin.RequiresOptIn
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, built-in-annotations, kotlin-requires-opt-in -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: RequiresOptIn annotation with message and level fields compiles
 */

// FILE: marker17541.kt

// TESTCASE NUMBER: 1
@RequiresOptIn(message = "Experimental API", level = RequiresOptIn.Level.WARNING)
annotation class ExperimentalApi17541

@RequiresOptIn(message = "Unstable API", level = RequiresOptIn.Level.ERROR)
annotation class UnstableApi17541

// FILE: usage17541.kt

@ExperimentalApi17541
class ExperimentalClass17541

@UnstableApi17541
class UnstableClass17541
