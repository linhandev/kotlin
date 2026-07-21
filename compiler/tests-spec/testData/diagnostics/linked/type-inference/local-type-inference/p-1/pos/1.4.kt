// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, local-type-inference -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: free type variable without explicit constraints resolves to optimal upper bound Any
 * HELPERS: checkType
 */

fun <T> sup142(): T = null!!

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<Any?>(sup142())
}
