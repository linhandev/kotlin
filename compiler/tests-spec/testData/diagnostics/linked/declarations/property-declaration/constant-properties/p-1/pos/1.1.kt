// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, constant-properties -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: const properties with compile-time initializer at top level or in object
 */

// TESTCASE NUMBER: 1
const val answer = 2 * 21

// TESTCASE NUMBER: 2
const val message = "Hello World!"

// TESTCASE NUMBER: 3
const val calculated = answer + 45

// TESTCASE NUMBER: 4
object Constants {
    const val flag = true
    const val ratio = 1.5f
}
