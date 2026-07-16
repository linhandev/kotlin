// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: when on Result512.Ok and Result512.Err exhausts sealed Result512
 */

sealed class Result512 {
    class Ok(val value: Int) : Result512()
    class Err(val message: String) : Result512()
}

// TESTCASE NUMBER: 1
fun case1(value: Result512): String {
    return when (value) {
        is Result512.Ok -> value.value.toString()
        is Result512.Err -> value.message
    }
}
