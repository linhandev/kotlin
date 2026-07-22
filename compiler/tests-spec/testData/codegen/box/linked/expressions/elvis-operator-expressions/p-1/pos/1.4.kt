// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, elvis-operator-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: elvis returns left-hand side when it is not reference equal to null
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val b: String? = "X"
    if ((b ?: "OK") != "X") return "NOK"
    return "OK"
}
