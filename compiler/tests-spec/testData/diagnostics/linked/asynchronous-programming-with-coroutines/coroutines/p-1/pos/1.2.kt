// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, coroutines -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: nested suspend calls through coroutine builder compile
 */

// TESTCASE NUMBER: 1
fun builder18042(block: suspend () -> String) {}

fun use18042() {
    builder18042 {
        step18042()
    }
}

suspend fun step18042(): String = "OK"

fun case_1() {
    use18042()
}
