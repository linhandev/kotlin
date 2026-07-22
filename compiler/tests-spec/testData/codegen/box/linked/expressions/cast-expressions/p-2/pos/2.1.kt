// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, cast-expressions -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: as and as? casts evaluate and return cast target type when cast succeeds
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val value: Any = "x"
    if ((value as String) != "x") return "NOK"
    if ((value as? String) != "x") return "NOK"
    return "OK"
}
