// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 122 -> sentence 122
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 41 -> sentence 41
 * syntax-and-grammar, syntax-grammar -> paragraph 34 -> sentence 34
 * NUMBER: 3
 * DESCRIPTION: anonymousFunction suspend modifier
 */
package syntax.grammar.p122.pos3

import kotlin.coroutines.*
import kotlin.coroutines.intrinsics.*

// TESTCASE NUMBER: 1
fun box(): String {
    var outcome = -1
    val fn: suspend () -> Int = suspend {
        41 + 1
    }
    fn.createCoroutineUnintercepted(object : Continuation<Int> {
        override val context: CoroutineContext = EmptyCoroutineContext
        override fun resumeWith(result: Result<Int>) {
            outcome = result.getOrDefault(-1)
        }
    }).resumeWith(Result.success(Unit))
    return if (outcome == 42) "OK" else "NOK"
}
