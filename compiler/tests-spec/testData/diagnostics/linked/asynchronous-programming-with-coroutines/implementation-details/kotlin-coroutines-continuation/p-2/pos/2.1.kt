// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, implementation-details, kotlin-coroutines-continuation -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: custom CoroutineContext.Element with companion Key
 */

import kotlin.coroutines.*

// TESTCASE NUMBER: 1
class Element18061 : CoroutineContext.Element {
    companion object Key : CoroutineContext.Key<Element18061>

    override val key: CoroutineContext.Key<*> = Key
}

fun case_1() {
    val e = Element18061()
}
