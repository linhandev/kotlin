// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT
// WITH_STDLIB

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, bare-type-argument-inference -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: bare type argument inference — simple non-nullable T infers List<Int> from List subject type
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val xs: List<Int> = listOf(1)
    if (<!USELESS_IS_CHECK!>xs is List<!>) {
        checkSubtype<List<Int>>(xs)
    }
}
