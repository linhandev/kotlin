/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, receivers -> paragraph 4 -> sentence 4
 * NUMBER: 2
 * DESCRIPTION: local extension callable has extension receiver only without dispatch receiver
 */

// TESTCASE NUMBER: 1
fun box(): String {
    fun String.localExt1104(): Int = length
    val result = "OK".localExt1104()
    return if (result == 2) "OK" else "NOK: $result"
}
