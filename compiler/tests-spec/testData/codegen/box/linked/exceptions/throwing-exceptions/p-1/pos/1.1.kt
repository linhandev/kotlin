/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: exceptions, throwing-exceptions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: throw inspects the innermost active try-block and enters its matching catch with the thrown value
 */
// TESTCASE NUMBER: 1

fun box(): String {
    val result = try {
        try {
            throw IllegalStateException("inner-161")
        } catch (e: IllegalStateException) {
            if (e.message != "inner-161") return "NOK: unexpected inner message ${e.message}"
            "inner-caught"
        }
    } catch (_: IllegalStateException) {
        return "NOK: outer catch should not run"
    }
    return if (result == "inner-caught") "OK" else "NOK"
}
