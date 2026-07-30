// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 14 -> sentence 14
 *                overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: extension function call may omit argument with a default value
 */

// TESTCASE NUMBER: 1
fun String.padTo(len: Int = 10): String = padEnd(len)

fun box(): String {
    val s = "hi".padTo()
    if (s.length != 10) return "NOK"
    if (!s.startsWith("hi")) return "NOK"
    return "OK"
}
