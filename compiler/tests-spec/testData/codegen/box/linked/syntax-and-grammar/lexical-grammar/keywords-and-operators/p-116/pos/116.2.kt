// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 116 -> sentence 116
 * NUMBER: 2
 * DESCRIPTION: SUSPEND token in suspend member function declaration; member suspend call returns value
 */

import kotlin.coroutines.*
import kotlin.coroutines.intrinsics.*

class SuspendHolder116 {
    suspend fun value116(): String = "member116"
}

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
    val holder = SuspendHolder116()
    val value = runSuspend116 { holder.value116() }
    return if (value == "member116") "OK" else "NOK"
}
