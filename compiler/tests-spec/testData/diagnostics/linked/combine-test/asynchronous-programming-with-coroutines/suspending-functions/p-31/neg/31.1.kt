// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -NOTHING_TO_INLINE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 31 -> sentence 31
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 31 -> sentence 31
 *                expressions, function-literals, lambda-literals -> paragraph 31 -> sentence 31
 *                asynchronous-programming-with-coroutines, suspending-functions -> paragraph 31 -> sentence 31
 * NUMBER: 1
 * DESCRIPTION: noinline lambda inside inline fun still cannot call a suspend function from a suspend caller
 */

inline fun call56131(noinline block: () -> Int): Int = block()
suspend fun inner56131(): Int = 1

// TESTCASE NUMBER: 1
suspend fun case_1(): Int = call56131 { <!NON_LOCAL_SUSPENSION_POINT!>inner56131<!>() }
