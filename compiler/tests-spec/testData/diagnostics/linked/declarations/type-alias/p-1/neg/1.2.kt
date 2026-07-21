// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, type-alias -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: type alias cannot expand to visibility-inaccessible type
 */

// FILE: lib.kt
internal class InternalType

// FILE: use.kt
// TESTCASE NUMBER: 1
typealias <!EXPOSED_TYPEALIAS_EXPANDED_TYPE!>Exposed<!> = InternalType
