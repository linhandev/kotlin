// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, coroutine-intrinsics -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: createCoroutineUnintercepted does not start coroutine until resumeWith is called
 */
// TESTCASE NUMBER: 1

import kotlin.coroutines.*
import kotlin.coroutines.intrinsics.*

var started18168 = false

suspend fun work18168(): String {
    started18168 = true
    return "OK"
}

fun box(): String {
    started18168 = false
    var result: String? = null
    val coroutine = suspend { work18168() }.createCoroutineUnintercepted(object : Continuation<String> {
        override val context: CoroutineContext = EmptyCoroutineContext
        override fun resumeWith(res: Result<String>) {
            result = res.getOrThrow()
        }
    })
    if (started18168) return "started too early"
    coroutine.resumeWith(Result.success(Unit))
    return if (started18168 && result == "OK") "OK" else "fail"
}
