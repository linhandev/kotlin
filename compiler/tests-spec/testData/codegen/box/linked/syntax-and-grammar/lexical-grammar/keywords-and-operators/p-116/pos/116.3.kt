// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 116 -> sentence 116
 * NUMBER: 3
 * DESCRIPTION: SUSPEND token in suspend interface function with override; overridden suspend call returns value
 */

import kotlin.coroutines.*
import kotlin.coroutines.intrinsics.*

interface SuspendApi116 {
    suspend fun fetch116(): String
}

class SuspendApiImpl116 : SuspendApi116 {
    override suspend fun fetch116(): String = "override116"
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
    val api: SuspendApi116 = SuspendApiImpl116()
    val value = runSuspend116 { api.fetch116() }
    return if (value == "override116") "OK" else "NOK"
}
