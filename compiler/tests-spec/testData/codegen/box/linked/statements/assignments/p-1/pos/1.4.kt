// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, assignments -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: c.n = 7 assigns Container property at runtime
 */

class Container {
    var n = 0
}

// TESTCASE NUMBER: 1
fun box(): String {
    val c = Container()
    c.n = 7
    return if (c.n == 7) "OK" else "NOK"
}
