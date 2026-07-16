// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: runtime-type-information -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: type-checking against nullable type accepts kotlin.Nothing? runtime type of null
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(x: Any?) {
    checkSubtype<Boolean>(x is String?)
}
