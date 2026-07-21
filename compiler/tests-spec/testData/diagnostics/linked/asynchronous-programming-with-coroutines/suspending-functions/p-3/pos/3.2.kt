// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -SUSPENSION_CALL_MUST_BE_USED_AS_RETURN_VALUE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 3 -> sentence 3
 * NUMBER: 2
 * DESCRIPTION: non-suspending inline lambda may call suspend from suspending builder context
 */

// TESTCASE NUMBER: 1
suspend inline fun invoke18032(block: () -> Unit) {
    block()
}

suspend fun target18032(): String = "OK"

fun builder18032(c: suspend () -> Unit) {}

fun test18032() {
    builder18032 {
        invoke18032 {
            target18032()
        }
    }
}

fun case_1() {
    test18032()
}
