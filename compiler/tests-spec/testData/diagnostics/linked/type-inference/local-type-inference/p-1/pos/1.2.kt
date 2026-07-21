// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, local-type-inference -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: local type inference deduces generic type parameters from argument types
 * HELPERS: checkType
 */

fun <T> echo142(value: T): T = value

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<Int>(echo142(42))
}
