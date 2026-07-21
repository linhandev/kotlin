// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -UNUSED_TYPEALIAS_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, type-alias -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: type alias for function type and generic type alias with two parameters compile successfully
 */

// TESTCASE NUMBER: 1
typealias IntPredicate = (Int) -> Boolean

// TESTCASE NUMBER: 2
typealias StringMap<V> = Map<String, V>
