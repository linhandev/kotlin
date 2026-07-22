// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, elvis-operator-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 6
 * DESCRIPTION: a ?: 1 and b ?: 1 assign Any type with Int or String values
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val a: String? = null
    val x: Any = a ?: 1
    if (x != 1) return "NOK"
    val b: String? = "hi"
    val y: Any = b ?: 1
    if (y != "hi") return "NOK"
    return "OK"
}
