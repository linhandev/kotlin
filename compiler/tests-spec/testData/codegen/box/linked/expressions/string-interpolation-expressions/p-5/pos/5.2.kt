// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, string-interpolation-expressions -> paragraph 5 -> sentence 5
 * NUMBER: 2
 * DESCRIPTION: single-line string template supports qualified path inside interpolation
 */

// TESTCASE NUMBER: 1

class Holder(val value: Int)

fun box(): String {
    val h = Holder(7)
    return if ("${h.value}" == "7") "OK" else "NOK"
}
