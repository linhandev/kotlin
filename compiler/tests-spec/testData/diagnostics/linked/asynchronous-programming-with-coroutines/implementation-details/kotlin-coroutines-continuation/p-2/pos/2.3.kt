// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, implementation-details, kotlin-coroutines-continuation -> paragraph 2 -> sentence 2
 * NUMBER: 3
 * DESCRIPTION: CoroutineContext supports multiple elements combined with plus operator
 */

import kotlin.coroutines.*

// TESTCASE NUMBER: 1
class Element18063 : CoroutineContext.Element {
    companion object Key : CoroutineContext.Key<Element18063>

    override val key: CoroutineContext.Key<*> = Key
}

class Element18064 : CoroutineContext.Element {
    companion object Key : CoroutineContext.Key<Element18064>

    override val key: CoroutineContext.Key<*> = Key
}

fun combined18063(): CoroutineContext = Element18063() + Element18064()

fun case_1() {
    combined18063()
}
