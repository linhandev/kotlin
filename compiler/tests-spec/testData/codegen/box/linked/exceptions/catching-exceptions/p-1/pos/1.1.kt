/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: exceptions, catching-exceptions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: first catch clause whose exception type matches the thrown value is executed
 */
// TESTCASE NUMBER: 1

fun box(): String {
    val result = try {
        throw IllegalArgumentException()
    } catch (_: IllegalArgumentException) {
        "specific"
    } catch (_: Exception) {
        "general"
    }
    return if (result == "specific") "OK" else "NOK"
}
