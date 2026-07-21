// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -TOPLEVEL_TYPEALIASES_ONLY
// SKIP_TXT
// WITH_STDLIB

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, bare-type-argument-inference -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: bare type argument inference — constraint system has no solution for repeated type-parameter alias
 * HELPERS: checkType
 */

typealias MMTT144<T> = MutableMap<T, T>

// TESTCASE NUMBER: 1
fun case_1(x: Map<Any, Any>) = x is <!WRONG_NUMBER_OF_TYPE_ARGUMENTS!>MMTT144<!>
