// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -SUSPENSION_CALL_MUST_BE_USED_AS_RETURN_VALUE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, built-in-annotations, kotlin-restrict-suspension -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: RestrictsSuspension allows member suspending function calls on receiver
 */

// TESTCASE NUMBER: 1
@kotlin.coroutines.RestrictsSuspension
class RestrictedController17751 {
    suspend fun memberFun() {}
}

fun generate17751(f: suspend RestrictedController17751.() -> Unit) {}

fun test17751() {
    generate17751 {
        memberFun()
    }
}
