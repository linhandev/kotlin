// FIR_IDENTICAL
// LANGUAGE: +MixedNamedArgumentsInTheirOwnPosition
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: required parameter after non-last vararg must be passed explicitly
 */

// TESTCASE NUMBER: 1
fun tail(vararg items: Int, required: String) {}

fun missingRequired() {
    tail<!NO_VALUE_FOR_PARAMETER!>()<!>
}
