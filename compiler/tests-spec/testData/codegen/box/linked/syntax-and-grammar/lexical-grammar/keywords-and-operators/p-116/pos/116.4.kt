// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 116 -> sentence 116
 * NUMBER: 4
 * DESCRIPTION: SUSPEND token in suspend function type parameter; lambda passed to suspend parameter is invoked
 */

import kotlin.coroutines.*
import kotlin.coroutines.intrinsics.*

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

fun acceptSuspend116(block: suspend () -> String): String = runSuspend116(block)

// TESTCASE NUMBER: 1
fun box(): String {
    val value = acceptSuspend116 { "lambda116" }
    return if (value == "lambda116") "OK" else "NOK"
}
