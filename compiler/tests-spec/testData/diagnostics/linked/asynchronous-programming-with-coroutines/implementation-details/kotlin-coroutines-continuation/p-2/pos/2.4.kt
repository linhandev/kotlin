// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, implementation-details, kotlin-coroutines-continuation -> paragraph 2 -> sentence 2
 * NUMBER: 4
 * DESCRIPTION: CoroutineContext element can be retrieved by Key
 */

import kotlin.coroutines.*

// TESTCASE NUMBER: 1
class Element18066 : CoroutineContext.Element {
    companion object Key : CoroutineContext.Key<Element18066>

    override val key: CoroutineContext.Key<*> = Key
}

fun retrieve18066(context: CoroutineContext): Element18066? = context[Element18066.Key]

fun case_1() {
    val e = Element18066()
    retrieve18066(e)
}
