// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, overview -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: annotation parameter type mismatch is reported
 */

// TESTCASE NUMBER: 1
annotation class Marker17011(val value: Int)

@Marker17011(<!TYPE_MISMATCH!>"wrong"<!>)
class BadAnnotatedClass17011
