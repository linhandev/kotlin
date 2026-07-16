/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: exceptions, catching-exceptions -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: finally runs before exception propagates when no catch clause matches
 */
// TESTCASE NUMBER: 1

fun box(): String {
    var finallyRan = false
    try {
        try {
            throw RuntimeException()
        } catch (_: IllegalStateException) {
            return "NOK"
        } finally {
            finallyRan = true
        }
    } catch (_: RuntimeException) {
        return if (finallyRan) "OK" else "NOK"
    }
    return "NOK outer"
}
