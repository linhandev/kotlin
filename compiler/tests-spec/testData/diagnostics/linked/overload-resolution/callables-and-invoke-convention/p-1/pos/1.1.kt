// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, callables-and-invoke-convention -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: local function declaration is a function-like callable resolved as f()
 */

// TESTCASE NUMBER: 1
fun case_1(): String {
    fun local1152(): String = "OK"
    return local1152()
}
