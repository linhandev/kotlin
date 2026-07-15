// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 116 -> sentence 116
 * NUMBER: 1
 * DESCRIPTION: SUSPEND token in suspend top-level function declaration; suspend function runs and returns value
 */

import kotlin.coroutines.*
import kotlin.coroutines.intrinsics.*

suspend fun suspendTop116(): String = "top116"

fun runSuspend116(block: suspend () -> String): String {
    var result: String? = null
    val value = block.startCoroutineUninterceptedOrReturn(object : Continuation<String> {
        override val context: CoroutineContext = EmptyCoroutineContext
        override fun resumeWith(res: Result<String>) {
            result = res.getOrThrow()
        }
    })
    if (value === COROUTINE_SUSPENDED) return result!!
    return value as String
}

// TESTCASE NUMBER: 1
fun box(): String {
    val value = runSuspend116 { suspendTop116() }
    return if (value == "top116") "OK" else "NOK"
}
