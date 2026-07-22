// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, elvis-operator-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: elvis returns right-hand side when left-hand side is null
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val a: String? = null
    if ((a ?: "OK") != "OK") return "NOK"
    return "OK"
}
