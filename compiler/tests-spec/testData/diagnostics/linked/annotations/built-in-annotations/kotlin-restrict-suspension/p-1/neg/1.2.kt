// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -SUSPENSION_CALL_MUST_BE_USED_AS_RETURN_VALUE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, built-in-annotations, kotlin-restrict-suspension -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: restricted receiver suspend functions must be called on extension receiver not stored reference
 */

// TESTCASE NUMBER: 1
@kotlin.coroutines.RestrictsSuspension
class RestrictedController17762 {
    suspend fun memberFun17762() {}
}

fun generate17762(f: suspend RestrictedController17762.() -> Unit) {}

fun test17762() {
    generate17762 {
        val receiver = this
        receiver.<!ILLEGAL_RESTRICTED_SUSPENDING_FUNCTION_CALL!>memberFun17762<!>()
    }
}
