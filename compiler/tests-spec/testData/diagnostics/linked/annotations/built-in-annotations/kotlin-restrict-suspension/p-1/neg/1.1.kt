// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, built-in-annotations, kotlin-restrict-suspension -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: restricted suspending function cannot call arbitrary suspending functions
 */

// TESTCASE NUMBER: 1
@kotlin.coroutines.RestrictsSuspension
class RestrictedController17761

suspend fun Any?.extFun17761() {}
suspend fun suspendFun17761() {}

fun generate17761(c: suspend RestrictedController17761.() -> Unit) {}

fun test17761() {
    generate17761 {
        <!ILLEGAL_RESTRICTED_SUSPENDING_FUNCTION_CALL!>extFun17761<!>()
        <!ILLEGAL_RESTRICTED_SUSPENDING_FUNCTION_CALL!>suspendFun17761<!>()
    }
}
