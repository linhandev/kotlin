// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, implementation-details, kotlin-coroutines-continuation -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: CoroutineContext lookup with wrong Key yields incompatible element type
 */

import kotlin.coroutines.*

// TESTCASE NUMBER: 1
class Element18067 : CoroutineContext.Element {
    companion object Key : CoroutineContext.Key<Element18067>

    override val key: CoroutineContext.Key<*> = Key
}

class OtherElement18067 : CoroutineContext.Element {
    companion object Key : CoroutineContext.Key<OtherElement18067>

    override val key: CoroutineContext.Key<*> = Key
}

fun case_1() {
    fun lookup18067(context: CoroutineContext) {
        val other = context[OtherElement18067.Key]
        val value: Element18067 = <!TYPE_MISMATCH!>other<!>
    }
}
