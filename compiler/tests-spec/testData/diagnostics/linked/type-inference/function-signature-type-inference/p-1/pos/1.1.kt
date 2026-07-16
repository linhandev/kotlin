// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, function-signature-type-inference -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: function signature type inference deduces anonymous function parameter and return types
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val suffix = fun(s: String) = s.length
    checkSubtype<(String) -> Int>(suffix)
}
