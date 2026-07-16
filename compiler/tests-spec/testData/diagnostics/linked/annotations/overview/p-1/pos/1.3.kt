// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, overview -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: annotation on property declaration is valid metadata association
 */

// TESTCASE NUMBER: 1
annotation class Marker17003(val value: Int)

@Marker17003(1)
val annotatedProperty17003: Int = 1
