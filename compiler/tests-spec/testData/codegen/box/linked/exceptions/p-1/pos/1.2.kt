/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: exceptions -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: finally block executes after a matching catch clause handles the thrown exception
 */
// TESTCASE NUMBER: 1

fun box(): String {
    var finallyRan = false
    try {
        throw RuntimeException()
    } catch (_: RuntimeException) {
        // catch matches and handles the exception
    } finally {
        finallyRan = true
    }
    return if (finallyRan) "OK" else "NOK: finally did not run when catch matched"
}
