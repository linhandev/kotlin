/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: exceptions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: thrown exception is handled by matching catch clause in try-expression
 */
// TESTCASE NUMBER: 1

fun box(): String {
    return try {
        throw IllegalStateException("fail")
    } catch (e: IllegalStateException) {
        if (e.message == "fail") "OK" else "NOK: unexpected message ${e.message}"
    }
}
